package net.class101.server1.service;

import lombok.Builder;
import lombok.Getter;
import net.class101.server1.Main;
import net.class101.server1.code.ProjectCode;
import net.class101.server1.product.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ProductSalesService {

    @Getter
    static class SalesHistory {
        int productNumber;
        int orderCnt;
        int productStockCount;
        int productPrice;
        String productName;
        String kind;
        String salesState;
        String errMsg;
        Date eventTime;

        @Builder
        public SalesHistory(Product product, int orderCnt, String salesState, String errMsg, Date eventTime) {
            this.productNumber = product.getProductNumber();
            this.orderCnt = orderCnt;
            this.productStockCount = product.getProductStockCount();
            this.productPrice = product.getProductPrice();
            this.productName = product.getProductName();
            this.kind = product.getKind();
            this.salesState = salesState;
            this.errMsg = errMsg;
            this.eventTime = eventTime;
        }
    }

    List<SalesHistory> salesHistoryList = new ArrayList<>();

    public void order(List<Main.OrderRecord> orderRecordList) {
        List<SalesHistory> transactionalSalesHistoryList = new ArrayList<>();
        if (!isValidProductNumber(orderRecordList)) {
            System.out.println("Fail to sale (wrong product number input)");
            return;
        }
        for (Main.OrderRecord orderRecord : orderRecordList) {
            for (Product product : Product.productList) {
                if (product.getProductNumber() == orderRecord.getOrderedPrdtNo()) {
                    if (product.getProductStockCount() >= orderRecord.getOrderedPrdtCnt()) {
                        // 판매 성공
                        // 키트일 경우 count 차감
                        if (product.getKind().equals(ProjectCode.ProductKind.Kit.getCode()))
                            product.setProductStockCount(product.getProductStockCount() - orderRecord.getOrderedPrdtCnt());

                        transactionalSalesHistoryList.add(SalesHistory.builder()
                                .product(product)
                                .eventTime(new Date())
                                .orderCnt(orderRecord.getOrderedPrdtCnt())
                                .salesState(ProjectCode.SalesState.Success.getCode())
                                .errMsg(ProjectCode.SalesState.Success.getMsg())
                                .build());
                    } else {
                        // 판매 실패
                        System.out.println("Fail to sale (order amount over) -> productNumber: " + product.getProductNumber());
                        return;
                    }
                }
            }
        }
        // 판매 결과 보여줌
        showSaleResult(transactionalSalesHistoryList);
        // 전체 히스토리에 저장
        salesHistoryList.addAll(transactionalSalesHistoryList);
    }

    private boolean isValidProductNumber(List<Main.OrderRecord> orderRecordList) {
        List<Integer> orderPrdtNoList = orderRecordList.stream().map(Main.OrderRecord::getOrderedPrdtNo).collect(Collectors.toList());
        for (int orderPrdtNo : orderPrdtNoList) {
            if (!Product.productNumberSet.contains(orderPrdtNo))
                return false;
        }
        return true;
    }

    private void showSaleResult(List<SalesHistory> transactionalSalesHistoryList) {
        transactionalSalesHistoryList.stream().forEach(salesHistory -> {
            System.out.println(salesHistory.getProductName() + " - " + salesHistory.getOrderCnt() + "개");
        });
        transactionalSalesHistoryList.stream().forEach(salesHistory -> {
            System.out.println("주문금액: " + salesHistory.getOrderCnt() * salesHistory.getProductPrice() + "원");
        });
    }
}

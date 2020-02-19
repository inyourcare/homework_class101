package net.class101.server1.thread;

import lombok.Builder;
import lombok.Getter;
import net.class101.server1.code.ProjectCode;
import net.class101.server1.product.Product;
import net.class101.server1.service.ProductSalesService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProjectThread extends Thread {

    private static ProductSalesService productSalesService = new ProductSalesService();

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("입력:(o[order] 주문 , q[quit] 종료) : ");
            String command = sc.next();
            if (command.toUpperCase().equals(ProjectCode.CommandCode.Order.getCode())) {
                orderProcess(sc);
            } else if (command.toUpperCase().equals(ProjectCode.CommandCode.Quit.getCode())) {
                break;
            }
        }
    }

    @Getter
    public static class OrderRecord {
        int orderedPrdtNo;
        int orderedPrdtCnt;

        @Builder
        public OrderRecord(int orderedPrdtNo, int orderedPrdtCnt) {
            this.orderedPrdtNo = orderedPrdtNo;
            this.orderedPrdtCnt = orderedPrdtCnt;
        }
    }

    private static void orderProcess(Scanner sc) {
        int input_prdtNo;
        int input_prdtCnt;
        System.out.println("상품번호     상품명                                        판매가격     재고수");
        Product.productList.stream().forEach(System.out::println);

        List<OrderRecord> orderRecordList = new ArrayList<>();
        while (true) {
            System.out.println("상품번호: ");
            sc.nextLine(); // 엔터값이 남아있대나...;
            String command = sc.nextLine();
            if (command.equals(ProjectCode.CommandCode.SPACE.getCode()))
                break;
            if (!isDigitString(command))
                continue;
            input_prdtNo = Integer.parseInt(command);

            System.out.println("수량: ");
            command = sc.next();
            if (!isDigitString(command))
                continue;
            input_prdtCnt = Integer.parseInt(command);

            orderRecordList.add(OrderRecord.builder().orderedPrdtCnt(input_prdtCnt).orderedPrdtNo(input_prdtNo).build());

        }

        productSalesService.order(orderRecordList);
    }

    private static boolean isDigitString(String source) {
        if (source == null && source.length() == 0)
            return false;
        for (Character s : source.toCharArray()) {
            if (!Character.isDigit(s))
                return false;
        }
        return true;
    }
}

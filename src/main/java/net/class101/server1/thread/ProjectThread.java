package net.class101.server1.thread;

import lombok.Builder;
import lombok.Getter;
import net.class101.server1.code.ProjectCode;
import net.class101.server1.product.Product;
import net.class101.server1.service.ProductSalesService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProjectThread extends Thread {

    private static ProductSalesService productSalesService = new ProductSalesService();
    private Iterator<String> iterator;

    public void setIterator(Iterator<String> iterator) {
        this.iterator = iterator;
    }

    @Override
    public void run() {
//        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("입력:(o[order] 주문 , q[quit] 종료) : ");
//            String command = sc.next();
            String command = iterator.next();
            if (command.toUpperCase().equals(ProjectCode.CommandCode.Order.getCode())) {
                orderProcess();
            } else if (command.toUpperCase().equals(ProjectCode.CommandCode.Quit.getCode())) {
                break;
            }
        }
        System.out.println("종료되었습니다.");
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

    private void orderProcess() {
//        Scanner sc = new Scanner(System.in);
        int input_prdtNo;
        int input_prdtCnt;
        System.out.println("상품번호     상품명                                        판매가격     재고수");
        Product.productList.stream().forEach(System.out::println);

        List<OrderRecord> orderRecordList = new ArrayList<>();
        while (true) {
            System.out.println("상품번호: ");
//            sc.nextLine(); // 엔터값이 남아있대나...;
//            String command = sc.nextLine();
            String command = iterator.next();
            if (command.equals(ProjectCode.CommandCode.SPACE.getCode()))
                break;
            if (!isDigitString(command))
                continue;
            input_prdtNo = Integer.parseInt(command);

            System.out.println("수량: ");
//            command = sc.next();
            command = iterator.next();
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

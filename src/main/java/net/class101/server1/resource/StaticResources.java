package net.class101.server1.resource;

import net.class101.server1.product.ClassProduct;
import net.class101.server1.product.KitProduct;
import net.class101.server1.product.Product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StaticResources {

    public static List<Product> productList = new ArrayList<>();
    public static Set<Integer> productNumberSet = new HashSet<>();

    public static void init() {
        productList.add(new ClassProduct(16374, "스마트스토어로 월 100만원 만들기, 평범한 사람이 돈을 만드는 비법", 151950, 99999));
        productList.add(new ClassProduct(26825, "해금, 특별하고 아름다운 나만의 반려악기", 114500, 99999));
        productList.add(new ClassProduct(65625, "일상에 따뜻한 숨결을 불어넣어요, 반지수와 함께하는 아이패드 드로잉", 174500, 99999));
        productList.add(new ClassProduct(55527, "코바늘로 인형을 만들자! 시은맘의 꼼지락 작업실", 299500, 99999));
        productList.add(new ClassProduct(2344, "일상 유튜버 슛뚜의 감성을 그대로. 영화같은 브이로그 제작법", 184500, 99999));
        productList.add(new ClassProduct(53144, "여행 드로잉, 꿈만 꾸지 마세요. 핀든아트와 여행, 그리다", 249500, 99999));
        productList.add(new ClassProduct(83791, "월급으로 만족하지 못하는 분들을 위한 아마존/이베이 입문", 178500, 99999));
        productList.add(new ClassProduct(96558, "사진 입문자를 위한 쉽게 배우고 빨리 써먹는 사진과 라이트룸", 234500, 99999));
        productList.add(new ClassProduct(45347, "일러스트레이터 집시의 매력적인 얼굴 그리기", 249500, 99999));
        productList.add(new ClassProduct(74218, "나만의 문방구를 차려요! 그리지영의 타블렛으로 굿즈 만들기", 191600, 99999));
        productList.add(new ClassProduct(28448, "당신도 할 수 있다! 베테랑 실무자가 알려주는 모션그래픽의 모든 것", 152200, 99999));
        productList.add(new KitProduct(91008, "작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트", 28000, 10));
        productList.add(new KitProduct(9236, "하루의 시작과 끝, 욕실의 포근함을 선사하는 천연 비누", 9900, 22));
        productList.add(new KitProduct(60538, "시작에 대한 부담을 덜다. 가격 절약 아이패드 특가전", 135800, 7));
        productList.add(new KitProduct(78156, "일상을 따뜻하게 채우는 씬캐쳐와 무드등", 45000, 16));
        productList.add(new KitProduct(97166, "이렇게 멋진 수채화 키트, 어때요? 클래스 101과 고넹이 화방이 기획한 3가지 수채화 키드", 96900, 5));
        productList.add(new KitProduct(58395, "선과 여백으로 채우는 2020년 캘린더와 엽서", 18620, 31));
        productList.add(new KitProduct(39712, "집에서 느끼는 겨울의 묵직한 포근함, 플랜테리어 아이템", 17600, 8));
        productList.add(new KitProduct(42031, "나만의 음악을 만들기 위한 장비 패키지", 209000, 2));
        productNumberSet = productList.stream().map(Product::getProductNumber).collect(Collectors.toSet());
    }
}

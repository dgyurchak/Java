//https://progcont.hu/progcont/100355/?pid=201298

import java.util.*;

public class Main {
    public static class Kupon implements Comparable<Kupon> {
        private String shop, product;
        private int discount;

        public Kupon(String shop, String product, int discount) {
            this.shop = shop;
            this.product = product;
            this.discount = discount;
        }

        public String getShop() {
            return shop;
        }

        public String getProduct() {
            return product;
        }

        public int getDiscount() {
            return discount;
        }

        @Override
        public int compareTo(Kupon kupon) {
            int res = Integer.compare(kupon.discount, discount);
            if (res == 0) {
                res = shop.compareTo(kupon.shop);
                if (res == 0) {
                    res = product.compareTo(kupon.product);
                }
            }
            return res;
        }
    }

    public static class SecondSort implements Comparable<SecondSort> {

        private String shop, product;
        private int discount;

        public SecondSort(String shop, String product, int discount) {
            this.shop = shop;
            this.product = product;
            this.discount = discount;
        }

        public String getShop() {
            return shop;
        }

        public String getProduct() {
            return product;
        }

        public int getDiscount() {
            return discount;
        }

        @Override
        public int compareTo(SecondSort secondSort) {
            int res = shop.compareTo(secondSort.shop);
            if (res == 0) {
                res = Integer.compare(secondSort.discount, discount);
                if (res == 0) {
                    res = product.compareTo(secondSort.product);
                }
            }
            return res;
        }

    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Kupon> list = new ArrayList<>();
        List<SecondSort> list1 = new ArrayList<>();
        int n = Integer.parseInt(sc.nextLine());
        String[] s;

        for (int i = 0; i < n; i++) {
            s = sc.nextLine().split(";");
            list.add(new Kupon(s[0], s[1], Integer.parseInt(s[2])));
            list1.add(new SecondSort(s[0], s[1], Integer.parseInt(s[2])));

        }

        Collections.sort(list);
        Collections.sort(list1);

        for (Kupon k : list) {
            System.out.println(k.getProduct() + " (" + k.getShop() + "): " + k.getDiscount() + "%");
        }
        System.out.println();
        for (SecondSort ss : list1) {
            System.out.println(ss.getProduct() + " (" + ss.getShop() + "): " + ss.getDiscount() + "%");
        }

    }

}

public class Main {

    public static void main(String[] args) {
        Kutuphane kutuphane = new Kutuphane();
        kutuphane.kitapEkle(new KitapBilim("123-456-789", "Bilimin Aydınlığında", "Ayşe Yılmaz", 2020, Durum.OduncAlinabilir));
        kutuphane.kitapEkle(new KitapRoman("987-654-321", "Gizemli Adanın Sırrı", "Ahmet Demir", 2018, Durum.OduncAlinabilir));
        kutuphane.kitapEkle(new KitapTarih("456-789-123", "Tarihin Derinliklerinde", "Mehmet Öz", 2022, Durum.OduncAlinabilir));
        Uye uye1 = new Uye("Elif Kaya");
        kutuphane.uyeEkle(uye1);
        Uye uye2 = new Uye("Mert Yılmaz");
        kutuphane.uyeEkle(uye2);
        System.out.println("Kitap ödünç alma işlemi:");
        kutuphane.kitapOduncVer("123-456-789", uye1);
        kutuphane.katalogGoster();
        System.out.println("\nKitap iade işlemi:");
        kutuphane.kitapIade("123-456-789", uye1);
        kutuphane.katalogGoster();
    }
}
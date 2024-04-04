import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Kutuphane {
    private List<Kitap> kitaplar = new ArrayList(); //Kütüphanedeki kitapların listesini tutar.
    private List<Uye> uyeler = new ArrayList(); //Kütüphaneye üye olan kişilerin listesini tutar.


    public void kitapEkle(Kitap kitap) { // Kütüphaneye yeni bir kitap ekler.

        this.kitaplar.add(kitap);
    }

    public void uyeEkle(Uye uye) { //  Kütüphaneye yeni bir üye ekler.

        this.uyeler.add(uye);
    }

    public void kitapOduncVer(String ISBN, Uye uye) { //Bir üyeye belirtilen ISBN numaralı kitabı ödünç verir.
        Kitap kitap = (Kitap) this.kitaplar.stream().filter((k) -> { //Kitap kitaplar listesinden ISBN numarasına göre bulunur.
            //kitaplar listesi stream() metoduyla işlenir.her kitap için filter fonk. kullanılır. Eşleşen ilk kitap findFirst() metodu ile bulunur.
            return k.getISBN().equals(ISBN) && k.getDurum() == Durum.OduncAlinabilir;  //Kitap ödünç alınabilir durumdaysa üyenin oduncAlinanKitaplar listesine eklenir
        }).findFirst().orElse(null); //Eşleşen kitap bulunamadıysa null değeri döndürülür.

        // Hata Kontrolü ve Güvenli Dönüştürme
        if (kitap == null) {
            System.out.println("Kitap bulunamadı veya şu anda ödünç alınamaz.");
            return;
        } else if (!(kitap instanceof Kitap)) {  //findFirst() metodundan gelen değeri Kitap tipine dönüştürmeden önce instanceof operatörü kullanarak tipinin doğrulanmasını sağlar.
            System.out.println("Beklenmedik bir hata oluştu. Lütfen tekrar deneyin."); // Kitap tipi Kitap değilse hata oluşur.
            return;
        }

        // Kitap ödünç verme işlemi
        uye.kitapOduncAl(kitap);

    }

    public void kitapIade(String ISBN, Uye uye) { //kitapIade(String ISBN, Uye uye): Bir üyeden belirtilen ISBN numaralı kitabı iade alır.
        Kitap kitap = (Kitap) uye.getOduncAlinanKitaplar().stream().filter((k) -> {  //Kitap üyenin oduncAlinanKitaplar listesinden ISBN numarasına göre bulunur.
            return k.getISBN().equals(ISBN);
        }).findFirst().orElse(null);

        // Hata Kontrolü ve Güvenli Dönüştürme
        if (kitap == null) {
            System.out.println("Bu ISBN'e sahip bir kitap bu üye tarafından ödünç alınmamış.");
            return;
        } else if (!(kitap instanceof Kitap)) {
            System.out.println("Beklenmedik bir hata oluştu. Lütfen tekrar deneyin.");
            return;
        }

        // Kitap iade etme işlemi
        uye.kitapIadeEt(kitap);

    }

    public void katalogGoster() {
        System.out.println("Kütüphane Kataloğu:"); //Kütüphanedeki tüm kitapların bilgilerini listeler.
        this.kitaplar.forEach((k) -> {
            PrintStream var10000 = System.out;
            String var10001 = k.getISBN();
            var10000.println("ISBN: " + var10001 + ", Başlık: " + k.getBaslik() + ", Yazar: " + k.getYazar() + ", Yayın Yılı: " + k.getYayinYili() + ", Durum: " + String.valueOf(k.getDurum()));
        }); //Her kitap için, ISBN, başlık, yazar, yayın yılı ve durum bilgileri konsola yazdırılır.
        //String.valueOf() fonksiyonu, Durum enum değerini String'e dönüştürmek için kullanılır.
    }
}

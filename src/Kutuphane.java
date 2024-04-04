import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Kutuphane {
    private List<Kitap> kitaplar = new ArrayList();
    private List<Uye> uyeler = new ArrayList();


    public void kitapEkle(Kitap kitap) {

        this.kitaplar.add(kitap);
    }

    public void uyeEkle(Uye uye) {

        this.uyeler.add(uye);
    }

    public void kitapOduncVer(String ISBN, Uye uye) {
        Kitap kitap = (Kitap) this.kitaplar.stream().filter((k) -> {
            return k.getISBN().equals(ISBN) && k.getDurum() == Durum.OduncAlinabilir;
        }).findFirst().orElse(null);

        // Hata Kontrolü ve Güvenli Dönüştürme
        if (kitap == null) {
            System.out.println("Kitap bulunamadı veya şu anda ödünç alınamaz.");
            return;
        } else if (!(kitap instanceof Kitap)) {
            System.out.println("Beklenmedik bir hata oluştu. Lütfen tekrar deneyin.");
            return;
        }

        // Kitap ödünç verme işlemi
        uye.kitapOduncAl(kitap);

    }

    public void kitapIade(String ISBN, Uye uye) {
        Kitap kitap = (Kitap) uye.getOduncAlinanKitaplar().stream().filter((k) -> {
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
        System.out.println("Kütüphane Kataloğu:");
        this.kitaplar.forEach((k) -> {
            PrintStream var10000 = System.out;
            String var10001 = k.getISBN();
            var10000.println("ISBN: " + var10001 + ", Başlık: " + k.getBaslik() + ", Yazar: " + k.getYazar() + ", Yayın Yılı: " + k.getYayinYili() + ", Durum: " + String.valueOf(k.getDurum()));
        });
    }
}

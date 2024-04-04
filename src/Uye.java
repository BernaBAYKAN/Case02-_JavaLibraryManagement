import java.util.ArrayList;
import java.util.List;

public class Uye implements IUye {
    private String isim; //üyenin adı
    private List<Kitap> oduncAlinanKitaplar=new ArrayList(); //Üyenin ödünç aldığı kitapların listesini tutar.

    public Uye(String isim) {
        this.isim = isim;
    }
    public void kitapOduncAl(Kitap kitap){ //Bir kitap ödünç alır.
        if (kitap.getDurum() == Durum.OduncAlinabilir) { //Kitabın durumu Durum.OduncAlinabilir ise:
            kitap.setDurum(Durum.OduncVerildi); //Kitabın durumunu Durum.OduncVerildi olarak değiştirir.
            this.oduncAlinanKitaplar.add(kitap); //Kitabı oduncAlinanKitaplar listesine ekler.
            System.out.println(kitap.getBaslik() + " kitabı ödünç alındı.");
        } else {
            System.out.println(kitap.getBaslik() + " kitabı şu anda mevcut değil."); //Aksi takdirde:Kitabın bu üye tarafından ödünç alınmadığını belirten mesaj gösterir.
        }
    }
    public void kitapIadeEt(Kitap kitap) {
        if (this.oduncAlinanKitaplar.remove(kitap)) {
            kitap.setDurum(Durum.OduncAlinabilir);
            System.out.println(kitap.getBaslik() + " kitabı iade edildi.");
        } else {
            System.out.println("Bu kitap bu üye tarafından ödünç alınmamış.");
        }

    }

    public List<Kitap> getOduncAlinanKitaplar() { //Üyenin ödünç aldığı kitapların listesini döndürür.

        return this.oduncAlinanKitaplar;
    }

    public String getIsim() { // Üyenin adını döndürür.

        return this.isim;
    }
}

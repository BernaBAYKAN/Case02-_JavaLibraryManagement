import java.util.ArrayList;
import java.util.List;

public class Uye implements IUye {
    private String isim;
    private List<Kitap> oduncAlinanKitaplar=new ArrayList();

    public Uye(String isim) {
        this.isim = isim;
    }
    public void kitapOduncAl(Kitap kitap){
        if (kitap.getDurum() == Durum.OduncAlinabilir) {
            kitap.setDurum(Durum.OduncVerildi);
            this.oduncAlinanKitaplar.add(kitap);
            System.out.println(kitap.getBaslik() + " kitabı ödünç alındı.");
        } else {
            System.out.println(kitap.getBaslik() + " kitabı şu anda mevcut değil.");
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

    public List<Kitap> getOduncAlinanKitaplar() {

        return this.oduncAlinanKitaplar;
    }

    public String getIsim() {

        return this.isim;
    }
}

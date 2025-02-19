import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args)  {
        String reset = "\u001B[0m";
        String sari = "\u001B[38;5;226m";
        String kirmizi = "\u001B[31m";
        String yesil = "\u001B[92m";
        String kYesil = "\u001B[32m";
        String mavi = "\u001B[34m";
        String mor = "\u001B[35m";
        String turuncu = "\u001B[38;5;208m";


        Scanner scan=new Scanner(System.in);

        ArrayList<Araba> durum = new ArrayList<>();
        Queue<Araba> kuyruk = new LinkedList<>();

        Otopark otopark = new Otopark();
        Random rnd = new Random();

        String girdi;
        int gun=1;



        System.out.println(sari+"MERİÇ OTOPARK'A HOŞGELDİNİZ"+reset);
        do{
            otopark.olusturYer();
            System.out.println("---------GÜN:"+gun+"---------");
            for (int i = 9; i < 24; i++) {
                System.out.println("----------SAAT:"+i+"---------");
                for(int j=0;j<60;j++){
                    LocalTime zaman = LocalTime.of(i, j);
                    //Thread.sleep(50);//Sunum esnasında kaldırılırsa daha rahat kontrol edilir.
                    for(int g=0;g<otopark.getYerler().size();g++){
                        if(!kuyruk.isEmpty()&&!otopark.getYerler().isEmpty()){
                            System.out.println(sari+"----------Saat "+zaman+"---------"+reset);
                            durum.add(kuyruk.poll());
                            durum.getLast().setYer(otopark.setDoldurYer());
                            durum.getLast().setSaatler(zaman);
                            System.out.println(yesil+"GİRİŞ " + durum.getLast() +mavi+ "\nBoş Yerler: " + otopark.getYerler()
                                    +"\nKuyruktaki Araç Sayısı: " + kuyruk.size()+reset);
                            g--;
                        }
                    }
                    int varMi =rnd.nextInt(0,10);
                    if(varMi==1 && i!= otopark.getKapanisSaati()){
                        Araba arac = new Araba(zaman, "Kuyrukta");
                        if (!otopark.getYerler().isEmpty()) {
                            System.out.println(sari+"----------Saat "+zaman+"---------"+reset);
                            arac.setYer(otopark.setDoldurYer());
                            durum.add(arac);
                            System.out.println(yesil+"GİRİŞ " + durum.getLast() +mavi +"\nBoş Yerler: " + otopark.getYerler()+reset);
                        }else if(i<20 && kuyruk.size()<20){
                            arac.setSaatler(LocalTime.of(0,0));
                            kuyruk.offer(arac);
                            System.out.println(sari+"----------Saat " + zaman + "---------\n" +turuncu+
                                    "BOŞ PARK YERİ YOK GELEN ARAÇ KUYRUĞA EKLENDİ.\n" +
                                    "Kuyruktaki Araç Sayısı: " + kuyruk.size() + "\n" +
                                    "Kuyruğa Giren Son Araç Plakası: " + arac.getPlaka()+reset);
                        }
                        else{
                            System.out.println(sari+"----------Saat " + zaman + "---------\n"+mor+"OTOPARK ARAÇ ALIMINA KAPALI."+reset);
                        }
                    }
                    if(!durum.isEmpty()){
                        for(int k=0;k<durum.size();k++){
                            if(durum.get(k).getCikisSaati().equals(zaman)){
                                System.out.println(sari+"----------Saat "+zaman+"---------"+reset);
                                otopark.setBosaltYer(durum.get(k).getYer());
                                switch (durum.get(k).getTip()){
                                    case("Sedan/Hatchback"):durum.get(k).setOdenen(otopark.odemeAl(durum.get(k).getKalmaSuresi(),0,durum.get(k).getOtoYikama()));
                                        break;
                                    case("Suv"):durum.get(k).setOdenen(otopark.odemeAl(durum.get(k).getKalmaSuresi(),1,durum.get(k).getOtoYikama()));
                                        break;
                                    case("Motosiklet"):durum.get(k).setOdenen(otopark.odemeAl(durum.get(k).getKalmaSuresi(),2,durum.get(k).getOtoYikama()));
                                        break;
                                    case("Minivan"):durum.get(k).setOdenen(otopark.odemeAl(durum.get(k).getKalmaSuresi(),3,durum.get(k).getOtoYikama()));
                                        break;
                                }
                                System.out.println(kirmizi+"ÇIKIŞ "+durum.remove(k)+reset);
                                System.out.println(mavi +"Boş Yerler: " + otopark.getYerler()+reset);
                                k--;
                            }
                        }
                    }
                }
                System.out.println("----------------------------------------------------");
                System.out.println(mavi+otopark.getYerler()+reset);
                for (Araba araba : durum) {//Her saat sonu, boş yerleri ve otoparktaki araçları görüntüler.
                    System.out.println(araba);}
                System.out.println("----------------------------------------------------");
            }
            System.out.println(mavi+"*******************************************************************************************\n"+sari+gun+". Güne Ait Veriler:\n"+turuncu+"Günlük Sedan/Hatchback Ziyaretçi : "+reset+otopark.getGelenGunlukDizi()[0]+turuncu+"  || SUV Ziyaretçi : "
                    +reset+otopark.getGelenGunlukDizi()[1]+turuncu+"  || Motosiklet Ziyaretçi : "+reset+otopark.getGelenGunlukDizi()[2]+turuncu+"  || Minivan Ziyaretçi : "
                    +reset+otopark.getGelenGunlukDizi()[3]+mavi+"\nGÜNLÜK Ziyaretçi Sayısı:"+otopark.getGelenGunluk()+kYesil+"\nGÜNLÜK Ciro: "+reset+otopark.getGunlukCiro()+reset+" TL  ||   "
                    +kirmizi+"GÜNLÜK Gider: "+reset+otopark.hizmetMaliyeti()+reset+" TL   ||"+yesil+"   GÜNLÜK Kar: "+reset+(otopark.getGunlukCiro()- otopark.hizmetMaliyeti())+" TL"+mavi+"\n*******************************************************************************************\n"+reset);

            otopark.setToplamCiro(otopark.getGunlukCiro());
            otopark.setToplamKar();
            otopark.setGelenToplam();

            System.out.println(mor+"*******************************************************************************************\n"+sari+"Tüm Günlere Ait Veriler: \n"+reset+gun+" Günlük"+mor+" SEDAN/HATCHBACK "+reset+"Ziyaretçi Sayısı: "+otopark.getGelenToplamDizi()[0]+"\n"+gun+" Günlük"+mor+" SUV "+reset+"Ziyaretçi Sayısı: "
                    +otopark.getGelenToplamDizi()[1]+"\n"+gun+" Günlük"+mor+" MOTOSİKLET "+reset+"Ziyaretçi Sayısı: "+otopark.getGelenToplamDizi()[2]+"\n"+gun+" Günlük"+mor+" MİNİVAN "+reset+"Ziyaretçi Sayısı: "
                    +otopark.getGelenToplamDizi()[3]+"\n"+mavi+gun+" Günlük TOPLAM Ziyaretçi Sayısı: "+otopark.getGelenToplam()+"\n"+kYesil+gun+" Günlük TOPLAM Ciro: "+reset+otopark.getToplamCiro()+" TL  ||  "
                    +kirmizi+gun+" Günlük TOPLAM Gider: "+reset+(otopark.hizmetMaliyeti()*gun)+" TL"+"  ||  "+yesil+gun+" Günlük TOPLAM Kar: "+reset+otopark.getToplamKar()+" TL"+mor+"\n*******************************************************************************************\n"+reset);

            otopark.gunlukSifirla();
            gun++;
            System.out.print("DİĞER GÜNE GEÇMEK İÇİN ENTER, ÇIKMAK İÇİN FARKLI BİR DEĞER GİRİNİZ:");
            girdi = scan.nextLine();

        }while(girdi.isEmpty());

        System.out.println(sari+"\n*******************************************************************************************\n"+"MERİÇ OTOPARK'A "+(gun-1)+" GÜNDE\n"+reset+"TOPLAM"+turuncu+" Sedan/Hatchback "+reset+"Ziyaretçi Sayısı: "+otopark.getGelenToplamDizi()[0]+"\nTOPLAM"+turuncu+" SUV "+reset+"Ziyaretçi Sayısı: "
                +otopark.getGelenToplamDizi()[1]+"\nTOPLAM"+turuncu+" Motosiklet "+reset+"Ziyaretçi Sayısı: "+otopark.getGelenToplamDizi()[2]+"\nTOPLAM"+turuncu+" Minivan "+reset+"Ziyaretçi Sayısı: "
                +otopark.getGelenToplamDizi()[3]+mavi+"\nTOPLAM Ziyaretçi Sayısı: "+otopark.getGelenToplam()+"\n"+kYesil+(gun-1)+" Günlük TOPLAM Ciro: "+reset+otopark.getToplamCiro()+" TL  ||  "
                +kirmizi+(gun-1)+" Günlük TOPLAM Gider: "+reset+(otopark.hizmetMaliyeti()*(gun-1))+" TL"+"  ||  "+yesil+(gun-1)+" Günlük TOPLAM Kar: "+reset+otopark.getToplamKar()+" TL\nAraç Başı Ortalama Kar: "
                +otopark.getToplamKar()/otopark.getGelenToplam()+" TL"+"\nGünlük Ortalama Kar: "+otopark.getToplamKar()/(gun-1)+"TL"+sari+"\n*******************************************************************************************");

    }
}
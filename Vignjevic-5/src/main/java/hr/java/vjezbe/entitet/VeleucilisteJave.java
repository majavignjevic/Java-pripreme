package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import hr.java.vjezbe.glavna.Glavna;
import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska {
    private static final Logger logger = LoggerFactory.getLogger(VeleucilisteJave.class);

    public VeleucilisteJave(String nazivUstanove, List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti, List<Ispit> ispiti) {
        super(nazivUstanove, predmeti, profesori, studenti, ispiti);
    }
    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, Integer ocjena_zavrsnog, Integer ocjena_obrane) {
        Integer zbroj = 0;
        Integer br = 0;
        for (int i = 0; i < ispiti.size(); i++) {
            Integer ocjena = ispiti.get(i).getOcjena().getNumber();
            zbroj = zbroj + ocjena;
            br++;
        }
        Integer prosjekOcjenaStudenta =(zbroj/br);
        BigDecimal konacna = BigDecimal.valueOf((prosjekOcjenaStudenta*2+ocjena_obrane+ocjena_zavrsnog)/4);
        return konacna;
    }

    @Override
    Student odrediNajuspijesnijegStudentaNaGodini(Integer godina) {

        BigDecimal najveciProsjek = BigDecimal.ZERO;
        Student najuspjesnijiStudent = null;

        for(Student student : getStudenti()){
            List<Ispit> ispiti = filtrirajIspitePoStudentu(getIspiti(), student);

            List<Ispit> ispitiUGodini = new ArrayList<>();
            Integer br=0;

            for(Ispit ispit : ispiti){
                if(godina.equals(ispit.getDatumIVrijeme().getYear())){          //ako godina ispita i zadana godina se slazu onda se ti ispiti spremaju u ispiti u godini
                    ispitiUGodini.add(new Ispit(ispit.getPred(),ispit.getStud(),ispit.getOcjena(), ispit.getDatumIVrijeme(), ispit.getDvorana()));
                    br++;
                }
            }
            try {
                BigDecimal projsjek = odrediProsjekOcjenaNaIspitima(ispitiUGodini);

                if (projsjek.compareTo(najveciProsjek) >= 0) {
                    najuspjesnijiStudent = student;
                }
            }
            catch (NemoguceOdreditiProsjekStudentaException ex1) {
                logger.error("poruka: ", ex1);
                System.out.println(ex1.getMessage());
            }

        }

        return najuspjesnijiStudent;
    }
}
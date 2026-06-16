package domaine;

import util.Util;

import java.util.*;

public class Livre {

    private Map<Plat.Type, SortedSet<Plat>> livres = new TreeMap<>();

    public boolean ajouterPlat(Plat plat) {
        Util.checkObject(plat);
        SortedSet<Plat> plats = livres.get(plat.getType());

        if (plats == null) {
            plats = new TreeSet<Plat>(new Comparator<Plat>() {
                @Override
                public int compare(Plat o1, Plat o2) {
                    int count = o1.getNiveauDeDifficulte().compareTo(o2.getNiveauDeDifficulte());

                    if(count == 0){
                        o1.getNom().compareTo(o2.getNom());
                    }
                    return count;
                }
            });
            this.livres.put(plat.getType() , plats);
        }

        plats.add(plat);
        return true;
    }

    public boolean supprimerPlat (Plat plat){
        Util.checkObject(plat);
        SortedSet<Plat> plats = livres.get(plat.getType());

        if(plats == null){
            return false;
        }

        boolean remove = plats.remove(plat);

        if(plats.isEmpty()){
            livres.remove(plat.getType());
        }

        return remove;
    }

    public SortedSet<Plat> getPlatsParType (Plat.Type type) {
        Util.checkObject(type);
        SortedSet<Plat> plats = livres.get(type);
        return Collections.unmodifiableSortedSet(plats);
    }

    public boolean contient (Plat plat){
        Util.checkObject(plat);

        if(livres.containsKey(plat.getType())){
            return livres.get(plat.getType()).contains(plat);
        }
        return false;
    }

    public Set<Plat> tousLesPlats () {
        Set<Plat> tousPlats = new TreeSet<>(Comparator.comparing(Plat::getType).thenComparing(Plat::getNiveauDeDifficulte).thenComparing(Plat::getNom));

        for (Set<Plat> value : livres.values()) {
            tousPlats.addAll(value);
        }

        return tousPlats;
    }

    public String toString () {
        StringBuilder str = new StringBuilder();

        for (Map.Entry<Plat.Type, SortedSet<Plat>> entry : this.livres.entrySet()) {
            str.append(entry.getKey().getNom()).append("\n");
            str.append("=========").append("\n");

            for (Plat plat : entry.getValue()) {
                str.append(plat.getNom()).append("\n");
            }
            str.append("\n");
        }

        return str.toString();
    }

}

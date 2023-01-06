package eu.telecomnancy.model.compact;

import java.util.ArrayList;
import eu.telecomnancy.model.DeckModel;

public class ApkgDeckModel implements Compact<DeckModel> {
    private Long id;
    private String name;
    private String description;
    private ArrayList<String[]> notes;

    public ApkgDeckModel() {
        this.id = null;
        this.name = null;
        this.description = null;
        this.notes = new ArrayList<String[]>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNotes(ArrayList<String[]> notes) {
        this.notes = notes;
    }

    @Override
    public Compact<DeckModel> from(DeckModel t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void to(DeckModel t) {
        t.setName(this.name);
        t.setDescription(this.description);
        for (String[] note : this.notes) {
            t.addCard(note[0], note[1]);
        }
    }
}

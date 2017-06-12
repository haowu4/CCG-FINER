package edu.illinois.cs.cogcomp.finer.components.kb_typer;

import edu.illinois.cs.cogcomp.core.datastructures.textannotation.Constituent;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.Sentence;
import edu.illinois.cs.cogcomp.finer.components.IFinerTyper;
import edu.illinois.cs.cogcomp.finer.datastructure.AnnotationReason;
import edu.illinois.cs.cogcomp.finer.datastructure.FineTypeConstituent;
import edu.illinois.cs.cogcomp.finer.datastructure.types.FinerType;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by haowu4 on 5/15/17.
 */
public class SimpleKBBiasTyper implements IFinerTyper {
    Map<String, List<FinerType>> surfaceToTypeDB;

    public SimpleKBBiasTyper(Map<String, List<FinerType>> surfaceToTypeDB) {
        this.surfaceToTypeDB = surfaceToTypeDB;
    }

    public List<FinerType> annotateSingleMention(Constituent mention, Sentence sentenc) {
        String surface = mention.getSurfaceForm();
        return surfaceToTypeDB.getOrDefault(surface, new ArrayList<>());
    }

    @Override
    public void annotate(List<FineTypeConstituent> mentions, Sentence sentence) {
        for (FineTypeConstituent mention : mentions) {
            List<FinerType> annotated = annotateSingleMention(mention, sentence);
            for (FinerType t : annotated) {
                if (mention.getCoarseType().isParentOf(t)) {
                    String typeName = t.getType();
                    mention.addFineType(t);
                    AnnotationReason reason = new AnnotationReason(SimpleKBBiasTyper.class);
                    mention.addReason(typeName, reason);
                }
            }
        }

    }
}

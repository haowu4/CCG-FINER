package edu.illinois.cs.cogcomp.finer.components;

import edu.illinois.cs.cogcomp.core.datastructures.textannotation.Constituent;

import java.util.List;

/**
 * Created by haowu4 on 5/15/17.
 */
public interface IFinerTyper {
    List<Constituent> annotate(List<Constituent> mentions);
}

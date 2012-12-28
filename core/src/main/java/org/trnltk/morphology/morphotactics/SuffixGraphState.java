package org.trnltk.morphology.morphotactics;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.trnltk.morphology.model.Suffix;
import zemberek3.lexicon.PrimaryPos;

import java.util.HashSet;

public class SuffixGraphState {
    private final String name;
    private final SuffixGraphStateType type;
    private final PrimaryPos primaryPos;
    private ImmutableSet<SuffixEdge> outEdges;

    public SuffixGraphState(String name, SuffixGraphStateType suffixGraphStateType, PrimaryPos primaryPos) {
        this.name = name;
        this.type = suffixGraphStateType;
        this.primaryPos = primaryPos;
        this.outEdges = ImmutableSet.of();
    }

    public String getName() {
        return name;
    }

    public PrimaryPos getPrimaryPos() {
        return primaryPos;
    }

    public SuffixGraphStateType getType() {
        return type;
    }

    public ImmutableSet<SuffixEdge> getOutEdges() {
        return this.outEdges;
    }

    public void addOutSuffix(Suffix suffix, SuffixGraphState suffixGraphState) {
        final HashSet<SuffixEdge> tempSet = Sets.newHashSet(outEdges);
        tempSet.add(new SuffixEdge(suffix, suffixGraphState));
        this.outEdges = ImmutableSet.copyOf(tempSet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuffixGraphState that = (SuffixGraphState) o;

        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "SuffixGraphState{" +
                "name='" + name + '\'' +
                '}';
    }
}
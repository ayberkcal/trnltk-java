/*
 * Copyright  2012  Ali Ok (aliokATapacheDOTorg)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.trnltk.morphology.lexicon;

import com.google.common.collect.ImmutableSet;
import org.junit.Before;
import org.junit.Test;
import org.trnltk.morphology.model.ImmutableRoot;
import org.trnltk.morphology.model.Lexeme;
import zemberek3.lexicon.tr.PhonAttr;
import zemberek3.lexicon.PrimaryPos;

import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

public class CircumflexConvertingRootGeneratorTest {

    CircumflexConvertingRootGenerator generator;

    PhonAttr LLV = PhonAttr.LastLetterVowel;
    PhonAttr LLC = PhonAttr.LastLetterConsonant;

    PhonAttr LVR = PhonAttr.LastVowelRounded;
    PhonAttr LVU = PhonAttr.LastVowelUnrounded;
    PhonAttr LVF = PhonAttr.LastVowelFrontal;
    PhonAttr LVB = PhonAttr.LastVowelBack;

    PhonAttr LLVless = PhonAttr.LastLetterVoiceless;
    PhonAttr LLVlessStop = PhonAttr.LastLetterVoicelessStop;
    PhonAttr LLNotVless = PhonAttr.LastLetterNotVoiceless;

    @Before
    public void setUp() throws Exception {
        generator = new CircumflexConvertingRootGenerator();
    }

    @Test
    public void shouldDoNothingSpecialWhenThereIsNoCircumflexChars() {
        {
            Lexeme lexeme = new Lexeme("hala", "hala", PrimaryPos.Noun, null, null);
            HashSet<ImmutableRoot> generatedRoots = generator.generate(lexeme);
            assertThat(generatedRoots, hasSize(1));
            assertThat(generatedRoots, hasItem(new ImmutableRoot("hala", lexeme, ImmutableSet.of(LVB, LLV, LLNotVless, LVU), null)));
        }
    }

    @Test
    public void shouldGenerateConvertingCircumflexes() {
        {
            Lexeme lexeme = new Lexeme("rüzgâr", "rüzgâr", PrimaryPos.Noun, null, null);
            HashSet<ImmutableRoot> generatedRoots = generator.generate(lexeme);
            assertThat(generatedRoots, hasSize(2));
            assertThat(generatedRoots, hasItem(new ImmutableRoot("rüzgar", lexeme, ImmutableSet.of(LVB, LLC, LLNotVless, LVU), null)));
            assertThat(generatedRoots, hasItem(new ImmutableRoot("rüzgâr", lexeme, ImmutableSet.of(LVB, LLC, LLNotVless, LVU), null)));
        }
        {
            Lexeme lexeme = new Lexeme("alenî", "alenî", PrimaryPos.Adjective, null, null);
            HashSet<ImmutableRoot> generatedRoots = generator.generate(lexeme);
            assertThat(generatedRoots, hasSize(2));
            assertThat(generatedRoots, hasItem(new ImmutableRoot("aleni", lexeme, ImmutableSet.of(LVF, LLV, LLNotVless, LVU), null)));
            assertThat(generatedRoots, hasItem(new ImmutableRoot("alenî", lexeme, ImmutableSet.of(LVF, LLV, LLNotVless, LVU), null)));
        }
        {
            Lexeme lexeme = new Lexeme("cülûs", "cülûs", PrimaryPos.Noun, null, null);
            HashSet<ImmutableRoot> generatedRoots = generator.generate(lexeme);
            assertThat(generatedRoots, hasSize(2));
            assertThat(generatedRoots, hasItem(new ImmutableRoot("cülus", lexeme, ImmutableSet.of(LVF, LLC, LLVless, LVR), null)));
            assertThat(generatedRoots, hasItem(new ImmutableRoot("cülûs", lexeme, ImmutableSet.of(LVF, LLC, LLVless, LVR), null)));
        }
    }

}
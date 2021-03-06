/*
 * Copyright  2013  Ali Ok (aliokATapacheDOTorg)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.trnltk.morphology.morphotactics;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.trnltk.model.suffix.SuffixFormSequence;
import org.trnltk.model.lexicon.PhoneticAttribute;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SuffixFormSequenceApplierTest {

    SuffixFormSequenceApplier applier;

    @Mock
    SuffixFormSequence.SuffixFormSequenceRule rule_A;

    @Mock
    SuffixFormSequence.SuffixFormSequenceRule rule_B;

    @Mock
    SuffixFormSequence suffixFormSequence;

    @Before
    public void setUp() throws Exception {
        applier = new SuffixFormSequenceApplier();

        when(rule_A.apply(Matchers.<ImmutableSet<PhoneticAttribute>>any())).thenReturn(null);

        when(rule_B.apply(ImmutableSet.of(PhoneticAttribute.LastLetterConsonant))).thenReturn('c');

        when(rule_B.apply(ImmutableSet.of(PhoneticAttribute.LastLetterVoicelessStop))).thenReturn('b');

        when(suffixFormSequence.getRules()).thenReturn(ImmutableList.of(rule_A, rule_B));
    }

    @Test
    public void shouldApply() throws Exception {
        assertThat(applier.apply(suffixFormSequence, ImmutableSet.<PhoneticAttribute>of()), equalTo(""));
        assertThat(applier.apply(suffixFormSequence, ImmutableSet.of(PhoneticAttribute.LastLetterConsonant)), equalTo("c"));
        assertThat(applier.apply(suffixFormSequence, ImmutableSet.of(PhoneticAttribute.LastLetterVoicelessStop)), equalTo("b"));
        assertThat(applier.apply(suffixFormSequence, ImmutableSet.of(PhoneticAttribute.LastLetterVowel)), equalTo(""));
    }
}

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

package org.trnltk.model.suffix;

/**
 * A shorthand for having a {@link Suffix} which changes the POS and
 * has a default {@link SuffixForm} that has no conditions and has a
 * suffix form of empty string.
 * <p/>
 * That means, it is an <a href="http://en.wikipedia.org/wiki/Epsilon-transitions">epsilon transition</a> in the FSM
 * which goes to a state with a different POS. Going to another POS is the difference from {@link FreeTransitionSuffix}.
 * <p/>
 * <code>ZeroTransitionSuffix</code>es are <b>put</b> in the string representation of a parse result.
 */
public class ZeroTransitionSuffix extends Suffix {
    private static final String PRETTY_NAME = "Zero";

    public ZeroTransitionSuffix(String name) {
        super(name, null, PRETTY_NAME, false);
        this.addSuffixForm("");
    }
}

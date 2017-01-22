/**
 * The MIT License
 *
 *   Copyright (c) 2017, Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *   THE SOFTWARE.
 */
package io.github.benas.randombeans;

import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.api.Randomizer;
import io.github.benas.randombeans.beans.Person;
import org.junit.Test;

import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomRandomizerTest {
    @Test
    public void theRandomrUseCustomRandomizerForFieldWithModifier() {
        final String stringConstant = "stringConstant";
        // Given
        EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .randomize(FieldDefinitionBuilder.field()
                        .hasModifiers(Modifier.TRANSIENT)
                        .ofType(String.class).get(), new Randomizer<String>() {
                    public String getRandomValue(){
                        return stringConstant;
                    }
                }).build();

        // When
        Person person = random.nextObject(Person.class);

        // Then
        assertThat(person.getEmail()).isEqualTo(stringConstant);
        assertThat(person.getName()).isNotEqualTo(stringConstant);
    }

    @Test
    public void theRandomrUseCustomRandomizerForFieldWithModifiers() {
        final String stringConstant = "stringConstant";
        // Given
        EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .randomize(FieldDefinitionBuilder.field()
                        .hasModifiers(Modifier.TRANSIENT | Modifier.PROTECTED)
                        .ofType(String.class).get(), new Randomizer<String>() {
                    public String getRandomValue(){
                        return stringConstant;
                    }
                }).build();

        // When
        Person person = random.nextObject(Person.class);

        // Then
        assertThat(person.getEmail()).isEqualTo(stringConstant);
        assertThat(person.getName()).isNotEqualTo(stringConstant);
    }
}
/*
 * Copyright 1&1 Internet AG, https://github.com/1and1/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.oneandone.jsoup.hamcrest;


import static net.oneandone.jsoup.hamcrest.fluent.DocumentAssertions.anElement;
import static net.oneandone.jsoup.hamcrest.fluent.DocumentAssertions.eachElement;
import static net.oneandone.jsoup.hamcrest.fluent.DocumentAssertions.elements;
import static net.oneandone.jsoup.hamcrest.fluent.JsoupAssertions.html;

import org.junit.Test;

public class FluentExampleTest extends HtmlBaseTest {

    @Test
    public void form_contains_a_csrf_token_hidden_field_jsoup() {
        html(source)
                .expect(anElement("form input[name=_csrf]").exists());
    }

    @Test
    public void form_has_a_submit_button() {
        html(source)
                .expect(elements("form input[type=submit], form button[type=submit]").hasCount(1));
    }

    @Test
    public void form_every_non_hidden_input_has_the_correct_class() {
        html(source)
                .expect(eachElement("form input:not([type=hidden])").hasCssClass("form-control"));
    }


    @Test
    public void form_every_non_hidden_input_has_the_correct_class_alternative() {
        html(source)
                .expect(elements("form input:not([type=hidden])").hasCount(3).each().hasCssClass("form-control"));
    }

    @Test
    public void form_every_non_hidden_input_has_corresponding_label() {
        html(source).expect(eachElement("form input:not([type=hidden])").predicate(
                element -> !element.id().isEmpty()
                        && element.parent().selectFirst("label[for=" + element.id() + "]") != null,
                description -> description.appendText("an input with corresponding label"),
                (element, description) -> description.appendText("element ").appendValue(element.cssSelector())
                        .appendText(" does not have a corresponding label.")
        ));
    }

    @Test
    public void document_title() {
        html(source)
                .expect(DocumentMatchers.hasTitle("Demo"));
    }

    @Test
    public void multiple_assertions_combined() {
        html(source)
                .expect(anElement("form input[name=_csrf]").exists())
                .and(elements("form input[type=submit], form button[type=submit]").hasCount(1))
                .and(eachElement("form input:not([type=hidden])").hasCssClass("form-control"))
                .and(DocumentMatchers.hasTitle("Demo"));
    }
}

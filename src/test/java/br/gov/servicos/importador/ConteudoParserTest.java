package br.gov.servicos.importador;

import br.gov.servicos.cms.ConteudoHtml;
import br.gov.servicos.cms.Markdown;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;

@RunWith(MockitoJUnitRunner.class)
public class ConteudoParserTest {

    @Mock
    Markdown markdown;

    @Test
    public void deveExtrairTextoPuroDeHtml() throws Exception {
        given(markdown.toHtml(anyObject())).willReturn(
                new ConteudoHtml()
                        .withId("foo")
                        .withTitulo("Foo")
                        .withHtml("<html><h2>Foo</h2><p>Parágrafo um.</p><p>Parágrafo dois.</p></html>")
        );

        String corpo = new ConteudoParser(markdown).conteudo("/conteudo/foo.md");

        assertThat(corpo, is("Parágrafo um. Parágrafo dois."));
    }
}
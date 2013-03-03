package siosio;

import com.intellij.codeInsight.template.impl.LiveTemplateLookupElement;
import com.intellij.codeInsight.template.impl.TemplateImpl;
import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.Nullable;

public class LiveTemplateDocumentProvider extends AbstractDocumentationProvider {

    @Override
    public PsiElement getDocumentationElementForLookupItem(
            PsiManager psiManager, Object object, PsiElement element) {
        if (object instanceof LiveTemplateLookupElement
                && ((LiveTemplateLookupElement) object).getTemplate() != null) {
            return new LiveTemplateElement(psiManager, (LiveTemplateLookupElement) object);
        }
        return null;
    }

    @Override
    public String generateDoc(PsiElement element,
            @Nullable PsiElement originalElement) {

        if (!(element instanceof LiveTemplateElement)) {
            return null;
        }

        LiveTemplateLookupElement liveTemplateLookupElement =
                ((LiveTemplateElement) element).getElement();
        TemplateImpl template = liveTemplateLookupElement.getTemplate();

        StringBuilder result = new StringBuilder();
        result.append("<html>");
        result.append("<h2>description</h2>");
        result.append("<pre>");
        result.append(StringUtil.escapeXml(template.getDescription()));
        result.append("</pre>");
        result.append("<h2>template text</h2>");
        result.append("<pre>");
        result.append(StringUtil.escapeXml(template.getString()));
        result.append("</pre>");
        result.append("</html>");
        return result.toString();
    }


}


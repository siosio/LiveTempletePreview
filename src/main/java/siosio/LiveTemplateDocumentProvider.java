package siosio;

import java.util.List;

import com.intellij.codeInsight.template.impl.LiveTemplateLookupElement;
import com.intellij.codeInsight.template.impl.LiveTemplateLookupElementImpl;
import com.intellij.codeInsight.template.impl.TemplateImpl;
import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.lang.documentation.ExternalDocumentationProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.Nullable;

public class LiveTemplateDocumentProvider extends AbstractDocumentationProvider implements ExternalDocumentationProvider {

    @Nullable
    @Override
    public PsiElement getDocumentationElementForLookupItem(
            PsiManager psiManager, Object object, PsiElement element) {
        if (!(object instanceof LiveTemplateLookupElementImpl)) {
            return null;
        }

        LiveTemplateLookupElementImpl lookupElement = (LiveTemplateLookupElementImpl) object;
        if (lookupElement.getTemplate() != null) {
            return new LiveTemplateElement(psiManager, lookupElement);
        }
        return null;
    }

    @Override
    public String generateDoc(PsiElement element,
            @Nullable PsiElement originalElement) {
        if (!(element instanceof LiveTemplateElement)) {
            return null;
        }
        LiveTemplateLookupElementImpl liveTemplateLookupElement =
                (LiveTemplateLookupElementImpl) ((LiveTemplateElement) element).getElement();
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

    @Nullable
    public String fetchExternalDocumentation(Project project,
            PsiElement element, List<String> strings) {
        return null;
    }

    public boolean hasDocumentationFor(PsiElement element,
            PsiElement element2) {
        return element instanceof LiveTemplateLookupElement;
    }

    public boolean canPromptToConfigureDocumentation(PsiElement element) {
        return false;
    }

    public void promptToConfigureDocumentation(PsiElement element) {
    }
}


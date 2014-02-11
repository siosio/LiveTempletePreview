package siosio;

import com.intellij.codeInsight.template.impl.LiveTemplateLookupElement;
import com.intellij.openapi.fileTypes.FileTypes;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.FakePsiElement;

public class LiveTemplateElement extends FakePsiElement {

    private LiveTemplateLookupElement element;
    private PsiManager manager;

    public LiveTemplateElement(
            PsiManager manager, LiveTemplateLookupElement element) {
        this.manager = manager;
        this.element = element;
    }

    public PsiElement getParent() {
        return null;
    }

    public LiveTemplateLookupElement getElement() {
        return element;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public PsiFile getContainingFile() {
        return PsiFileFactory.getInstance(getProject()).createFileFromText(
                "hoge.txt", FileTypes.PLAIN_TEXT, "");
    }

    @Override
    public PsiManager getManager() {
        return manager;
    }

    @Override
    public String getName() {
        return element.getLookupString();
    }
}

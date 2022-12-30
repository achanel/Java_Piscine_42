package edu.school21.annotations.processor;

import com.google.auto.service.AutoService;
import edu.school21.annotations.app.HtmlForm;
import edu.school21.annotations.app.HtmlInput;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

@SupportedAnnotationTypes({"edu.school21.annotations.HtmlForm", "edu.school21.annotations.HtmlInput"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class HtmlProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
        for (Element element : annotatedElements) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.MANDATORY_WARNING, "form " + element.getSimpleName(), element);
            for (Element subElement : element.getEnclosedElements()) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.MANDATORY_WARNING, "input " + subElement.getSimpleName(), subElement);
            }
            makeForm(element);
        }
        return true;
    }

    private void makeForm(Element element) {
        HtmlForm htmlForm = element.getAnnotation(HtmlForm.class);
        String filename = htmlForm.fileName();
        String action = htmlForm.action();
        String method = htmlForm.method();

        if (filename != null && !filename.isEmpty()) {
            FileObject file;
            try {
                file = processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT, "" , filename);
                System.out.println(file);
                Writer writer = file.openWriter();
                writer.write("<form action = " + "\"" + action
                        + "\" method = " + "\"" + method + "\"" + ">\n");

                for (Element subElement : element.getEnclosedElements()) {
                    HtmlInput htmlInput = subElement.getAnnotation(HtmlInput.class);
                    if (htmlInput != null) {
                        String type = htmlInput.type();
                        String name = htmlInput.name();
                        String placeholder = htmlInput.placeholder();
                        writer.write("<input type + " + "\"" + type + "\" name = "
                                + name + "\"" + " placeholder = " + "\""
                                + placeholder + "\"" + ">\n");
                    }
                }

                writer.write("input type = \"submit\" value = \"Send\">\n");
                writer.write("</form");
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.MANDATORY_WARNING, "form name not valid for " + element.getSimpleName());
        }
    }
}

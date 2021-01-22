package co.com.bancolombia.factory.helps;

import co.com.bancolombia.exceptions.CleanException;
import co.com.bancolombia.factory.ModuleBuilder;
import co.com.bancolombia.factory.ModuleFactory;
import co.com.bancolombia.utils.Utils;

import java.io.IOException;

public class HelpGeneric implements ModuleFactory {
    @Override
    public void buildModule(ModuleBuilder builder) throws IOException, CleanException {
        builder.loadPackage();
        String name = builder.getStringParam("task-param-name");
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("No name is set for GENERIC type, usage: gradle generateHelpPackage --name [name]");
        }
        String dashName = Utils.toDashName(name);
        builder.addParam("name-dash", dashName);
        builder.addParam("name-package", name.toLowerCase());
        builder.appendToSettings(dashName, "infrastructure/helps");
        builder.setupFromTemplate("helps/"+name);
    }
}

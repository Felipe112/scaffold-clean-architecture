package co.com.bancolombia.task;

import co.com.bancolombia.Constants.BooleanOption;
import co.com.bancolombia.exceptions.CleanException;
import co.com.bancolombia.factory.ModuleFactory;
import co.com.bancolombia.factory.adapters.ModuleFactoryDrivenAdapter;
import co.com.bancolombia.factory.adapters.ModuleFactoryDrivenAdapter.DrivenAdapterType;
import co.com.bancolombia.factory.entrypoints.EntryPointRestMvc;
import co.com.bancolombia.factory.entrypoints.ModuleFactoryEntryPoint;
import co.com.bancolombia.factory.helps.HelpGeneric;
import co.com.bancolombia.utils.Utils;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.options.Option;
import org.gradle.api.tasks.options.OptionValues;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateHelpPackageTask extends CleanArchitectureDefaultTask {
    private String name;

    @Option(option = "name", description = "Set help name when GENERIC type")
    public void setName(String name) {
        this.name = name;
    }

    @TaskAction
    public void generateHelpPackageTask() throws IOException, CleanException {
        if (name.isEmpty()) {
            printHelp();
            throw new IllegalArgumentException("No model name, usage: gradle generateHelpPackage --name [name]");
        }
        name = Utils.capitalize(name);

        HelpGeneric moduleFactory = new HelpGeneric();
        logger.lifecycle("Clean Architecture plugin version: {}", Utils.getVersionPlugin());
        logger.lifecycle("Help Name: {}", name);
        builder.loadPackage();
        builder.addParam("helpName", name.toLowerCase());
        builder.addParam("helpClassName", name);
        builder.setupFromTemplate("help");
        moduleFactory.buildModule(builder);
        builder.persist();

    }
}

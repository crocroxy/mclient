package gg.croxy.module;

import gg.croxy.module.combat.*;
import gg.croxy.module.utility.*;
import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private static ModuleManager INSTANCE;
    private final List<Module> modules = new ArrayList<>();

    private ModuleManager() {
        // Combat
        addModule(new CrystalAura());
        addModule(new KillAura());
        addModule(new AutoTotem());
        
        // Utility
        addModule(new AutoTool());
        
        // More modules will be added here
    }

    public static ModuleManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ModuleManager();
        }
        return INSTANCE;
    }

    private void addModule(Module module) {
        modules.add(module);
    }

    public List<Module> getModules() {
        return modules;
    }

    public Module getModule(String name) {
        return modules.stream()
            .filter(module -> module.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }
}
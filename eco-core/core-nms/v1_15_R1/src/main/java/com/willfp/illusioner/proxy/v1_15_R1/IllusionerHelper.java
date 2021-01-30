package com.willfp.illusioner.proxy.v1_15_R1;

import com.willfp.illusioner.proxy.proxies.EntityIllusionerProxy;
import com.willfp.illusioner.proxy.proxies.IllusionerHelperProxy;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftIllusioner;
import org.bukkit.entity.Illusioner;
import org.jetbrains.annotations.NotNull;

public class IllusionerHelper implements IllusionerHelperProxy {
    @Override
    public EntityIllusionerProxy spawn(@NotNull final Location location) {
        EntityIllusioner illusioner = new EntityIllusioner(location);
        ((CraftWorld) location.getWorld()).getHandle().addEntity(illusioner);
        return illusioner;
    }

    @Override
    public EntityIllusionerProxy adapt(@NotNull final Illusioner illusioner) {
        if (illusioner instanceof CraftIllusioner) {
            if (((CraftIllusioner) illusioner).getHandle() instanceof EntityIllusionerProxy) {
                return null;
            }
        } else {
            return null;
        }
        illusioner.remove();
        return spawn(illusioner.getLocation());
    }
}

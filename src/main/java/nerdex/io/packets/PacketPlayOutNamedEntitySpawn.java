package nerdex.io.packets;/*
 * SpigotUtils
 * Copyright (C) 2017 RapidTheNerd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_11_R1.*;

import java.io.IOException;
import java.util.List;

public class PacketPlayOutNamedEntitySpawn {

    private int a;
    private GameProfile b;
    private int c;
    private int d;
    private int e;
    private byte f;
    private byte g;
    private int h;
    private DataWatcher i;
    private List j;

    public PacketPlayOutNamedEntitySpawn(){}

    public PacketPlayOutNamedEntitySpawn(EntityHuman human){
        this.a = human.getId();
        this.b = human.getProfile();
        this.c = MathHelper.floor(human.locX * 32.0D);
        this.d = MathHelper.floor(human.locY * 32.0D);
        this.e = MathHelper.floor(human.locZ * 32.0D);
        this.f = (byte) ((int) (human.yaw * 256.0F / 360.0F));
        this.g = (byte) ((int) (human.pitch * 256.0F / 360.0F));
        ItemStack itemStack = human.inventory.getItemInHand();

        this.i = human.getDataWatcher();
    }
    public void b(PacketDataSerializer packetDataSerializer){
        packetDataSerializer.b(this.a);
        packetDataSerializer.a(this.b.getId());
        packetDataSerializer.a(this.b.getName());
        packetDataSerializer.writeInt(this.c);
        packetDataSerializer.writeInt(this.d);
        packetDataSerializer.writeInt(this.e);
        packetDataSerializer.writeByte(this.f);
        packetDataSerializer.writeByte(this.g);
        packetDataSerializer.writeShort(this.h);
        try {
            this.i.a(packetDataSerializer);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void a(PacketListenerPlayOut out){
        out.a((IChatBaseComponent) this);
    }

    public String b(){
        return String.format("id=%d, gameProfile=\'%s\', x=%.2f, y=%.2f, z=%.2f, carried=%d", new Object[] {
                Integer.valueOf(this.a), this.b, Float.valueOf((float) this.c / 32.0F), Float.valueOf((float) this.d / 32.0F),
                Float.valueOf((float) this.e / 32.0F), Integer.valueOf(this.h)});
    }
}

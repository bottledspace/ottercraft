package ca.otterspace.ottercraft;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.RiverBiome;
import net.minecraft.world.biome.SwampBiome;

import javax.annotation.Nullable;

public class OtterEntity extends WolfEntity {
    private static final DataParameter<Integer> OTTER_SUBSPECIES = EntityDataManager.createKey(OtterEntity.class, DataSerializers.VARINT);

    public enum Animation {
        IDLE,
        RUNNING
    };
    public Animation animation;
    public int frame;
    public int dframe;
    public OtterEntity(EntityType<? extends WolfEntity> type, World worldIn) {
        super(type, worldIn);
        this.animation = Animation.IDLE;
        this.frame = 0;
        this.dframe = 1;
    }
    public void setOtterSubspecies(int subspecies) { this.dataManager.set(OTTER_SUBSPECIES, subspecies); }
    public int getOtterSubspecies() {
        return this.dataManager.get(OTTER_SUBSPECIES);
    }
    protected void registerData() {
        super.registerData();
        this.dataManager.register(OTTER_SUBSPECIES, 0);
    }
    public void readAdditional(CompoundNBT compound) {
        this.setOtterSubspecies(compound.getInt("Subspecies"));
        super.readAdditional(compound);
    }
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("Subspecies", this.getOtterSubspecies());
    }
    public void livingTick() {
        super.livingTick();
        double dx = Math.abs(this.getMotion().getX());
        double dz = Math.abs(this.getMotion().getZ());

        this.animation = (dx*dx+dz*dz <= 0.001)? Animation.IDLE : Animation.RUNNING;

        this.frame = (this.frame + this.dframe) % 20;
        if (this.animation == Animation.RUNNING) {
            this.dframe = (this.dframe == 1) ? 2 : 1;
        }
        else
            this.dframe = 1;
    }
    public static class OtterData extends AgeableEntity.AgeableData {
        public final int subspecies;
        public OtterData(int subspecies) { this.subspecies = subspecies; }
    };
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int i;
        if (spawnDataIn instanceof OtterData)
            i = ((OtterData)spawnDataIn).subspecies;
        else {
            Biome biome = worldIn.getBiome(this.getPosition());
            if (biome instanceof SwampBiome)
                i = 1;
            else
                i = 0;
            spawnDataIn = new OtterData(i);
        }
        this.setOtterSubspecies(i);
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }
}

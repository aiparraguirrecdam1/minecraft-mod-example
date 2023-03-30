package es.mariaanasanz.ut7.mods.impl;

import es.mariaanasanz.ut7.mods.base.*;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkSource;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.lighting.LevelLightEngine;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.level.storage.WorldData;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.ticks.LevelTickAccess;
import net.minecraftforge.client.event.MovementInputUpdateEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

@Mod(DamMod.MOD_ID)
public class ExampleMod extends DamMod implements IBlockBreakEvent, IServerStartEvent,
        IItemPickupEvent, ILivingDamageEvent, IUseItemEvent,
        IInteractEvent, IMovementEvent, LevelAccessor {

    public ExampleMod(){
        super();
    }

    @Override
    public String autor() {
        return "Almudena Iparraguirre Castillo";
    }

    @Override
    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {

        BlockPos pos = event.getPos();
        BlockState tipo = event.getState();
        Block block = event.getState().getBlock();
        Player player = event.getPlayer();

        event.getLevel();
        destroyBlock(BlockPos.of(pos.getX() + 1), false);




        if (block == Blocks.GRASS_BLOCK || block == Blocks.SAND || block == Blocks.GRAVEL) {
            System.out.println(tipo);

            if (player.getMainHandItem().getItem().equals(Items.WOODEN_SHOVEL)) {
                System.out.println("El jugador tiene una pala de madera en la mano");

                System.out.println("Bloque destruido en la posicion " + pos);
            } else if (player.getMainHandItem().getItem().equals(Items.STONE_SHOVEL)) {
                System.out.println("El jugador tiene una pala de piedra en la mano");


                // Destruye un bloque en la posicion pos;


                System.out.println("Bloque destruido en la posicion " + pos + " X: " + (pos.getX() + 1));
                System.out.println("Bloque destruido en la posicion " + pos + " Y: " + (pos.getY() + 1));
                System.out.println("Bloque destruido en la posicion " + pos + " Z: " + (pos.getZ() + 1));
                System.out.println("Bloque destruido en la posicion " + pos + " X: " + (pos.getX() - 1));
                System.out.println("Bloque destruido en la posicion " + pos + " Y: " + (pos.getY() - 1));
                System.out.println("Bloque destruido en la posicion " + pos + " Z: " + (pos.getZ() - 1));


        }



            else if (player.getMainHandItem().getItem().equals(Items.IRON_SHOVEL)) {
            System.out.println("El jugador tiene una pala de hierro en la mano");
            if (block == Blocks.GRASS_BLOCK || block == Blocks.SAND || block == Blocks.GRAVEL) {
                System.out.println(tipo);
                // Destruye un bloque en la posicion pos
                destroyBlock(pos, false);

                for (int i = 1; i <= 2; i++) {
                    System.out.println("Bloque destruido en la posicion " + pos + " X: " + (pos.getX() + i));
                    System.out.println("Bloque destruido en la posicion " + pos + " Y: " + (pos.getY() + i));
                    System.out.println("Bloque destruido en la posicion " + pos + " Z: " + (pos.getZ() + i));
                    System.out.println("Bloque destruido en la posicion " + pos + " X: " + (pos.getX() - i));
                    System.out.println("Bloque destruido en la posicion " + pos + " Y: " + (pos.getY() - i));
                    System.out.println("Bloque destruido en la posicion " + pos + " Z: " + (pos.getZ() - i));
                    System.out.println();

                }
                event.getLevel().destroyBlock(BlockPos.of(pos.getX() + 1), false);
                event.getLevel().setBlock(BlockPos.of(pos.getX() + 1), Blocks.AIR.defaultBlockState(), 1);
                event.getLevel().blockUpdated(BlockPos.of(pos.getX() + 1), Blocks.AIR.defaultBlockState().getBlock());

                Minecraft.getInstance().level.destroyBlock(BlockPos.of(pos.getX() + 1), false);
                Minecraft.getInstance().level.setBlock(BlockPos.of(pos.getX() + 1), Blocks.AIR.defaultBlockState(), 1);
                Minecraft.getInstance().level.blockUpdated(BlockPos.of(pos.getX() + 1), Blocks.AIR.defaultBlockState().getBlock());

            } else {
                System.out.println("El bloque destruido no es tierra ni arena ni grava");
                System.out.println("El bloque no se puede destruir con una pala");
            }
        }

            else if (player.getMainHandItem().getItem().equals(Items.GOLDEN_SHOVEL)) {
            System.out.println("El jugador tiene una pala de oro en la mano");

                // Destruye un bloque en la posicion pos
                destroyBlock(pos, false);

                for (int i = 1; i <= 3; i++) {
                    System.out.println("Bloque destruido en la posicion " + pos + " X: " + (pos.getX() + i));
                    System.out.println("Bloque destruido en la posicion " + pos + " Y: " + (pos.getY() + i));
                    System.out.println("Bloque destruido en la posicion " + pos + " Z: " + (pos.getZ() + i));
                    System.out.println("Bloque destruido en la posicion " + pos + " X: " + (pos.getX() - i));
                    System.out.println("Bloque destruido en la posicion " + pos + " Y: " + (pos.getY() - i));
                    System.out.println("Bloque destruido en la posicion " + pos + " Z: " + (pos.getZ() - i));
                    System.out.println();
                }


        }



            else if (player.getMainHandItem().getItem().equals(Items.DIAMOND_SHOVEL)) {
            System.out.println("El jugador tiene una pala de diamante en la mano");

                // Destruye un bloque en la posicion pos
                destroyBlock(pos, false);

                for (int i = 1; i <= 4; i++) {
                    System.out.println("Bloque destruido en la posicion " + pos + " X: " + (pos.getX() + i));
                    System.out.println("Bloque destruido en la posicion " + pos + " Y: " + (pos.getY() + i));
                    System.out.println("Bloque destruido en la posicion " + pos + " Z: " + (pos.getZ() + i));
                    System.out.println("Bloque destruido en la posicion " + pos + " X: " + (pos.getX() - i));
                    System.out.println("Bloque destruido en la posicion " + pos + " Y: " + (pos.getY() - i));
                    System.out.println("Bloque destruido en la posicion " + pos + " Z: " + (pos.getZ() - i));
                    System.out.println();
                }

            }
        }



        else if (player.getMainHandItem().getItem().equals(Items.NETHERITE_SHOVEL)) {
            System.out.println("El jugador tiene una pala de netherita en la mano");
                // Destruye un bloque en la posicion pos
                destroyBlock(pos, false);

                for (int i = 1; i <= 5; i++) {
                    System.out.println("Bloque destruido en la posicion " + pos + " X: " + (pos.getX() + i));
                    System.out.println("Bloque destruido en la posicion " + pos + " Y: " + (pos.getY() + i));
                    System.out.println("Bloque destruido en la posicion " + pos + " Z: " + (pos.getZ() + i));
                    System.out.println("Bloque destruido en la posicion " + pos + " X: " + (pos.getX() - i));
                    System.out.println("Bloque destruido en la posicion " + pos + " Y: " + (pos.getY() - i));
                    System.out.println("Bloque destruido en la posicion " + pos + " Z: " + (pos.getZ() - i));
                    System.out.println();
                }


        }
            else {
                System.out.println("El bloque destruido no es tierra ni arena ni grava");
            }
        }


    @Override
    @SubscribeEvent
    public void onServerStart(ServerStartingEvent event) {
        LOGGER.info("Server starting");
    }

    @Override
    @SubscribeEvent
    public void onItemPickup(EntityItemPickupEvent event) {
        LOGGER.info("Item recogido");
        System.out.println("Item recogido");
    }

    @Override
    @SubscribeEvent
    public void onLivingDamage(LivingDamageEvent event) {
        System.out.println("evento LivingDamageEvent invocado "+event.getEntity().getClass()+" provocado por "+event.getSource().getEntity());
    }

    @Override
    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {
        System.out.println("evento LivingDeathEvent invocado "+event.getEntity().getClass()+" provocado por "+event.getSource().getEntity());

    }

    @Override
    @SubscribeEvent
    public void onUseItem(LivingEntityUseItemEvent event) {
        LOGGER.info("evento LivingEntityUseItemEvent invocado "+event.getEntity().getClass());
    }

    @Override
    @SubscribeEvent
    public void onPlayerTouch(PlayerInteractEvent.RightClickBlock event) {
        System.out.println("¡Has hecho click derecho!");
        BlockPos pos = event.getPos();
        BlockState state = event.getLevel().getBlockState(pos);
        Player player = event.getEntity();
        ItemStack heldItem = player.getMainHandItem();
        if (ItemStack.EMPTY.equals(heldItem)) {
            System.out.println("La mano esta vacia");
            if (state.getBlock().getName().getString().trim().toLowerCase().endsWith("log")) {
                System.out.println("¡Has hecho click sobre un tronco!");
            }
        }

        System.out.println("Datos del jugador: " + player.getMainHandItem());

        if (heldItem.equals(Items.WOODEN_SHOVEL)){
            System.out.println("El jugador tiene una pala en la mano");
        }
    }


    @SubscribeEvent
    public void onPlayerTouch(PlayerInteractEvent.LeftClickBlock event) {
        System.out.println("¡Has hecho click izquierdo!");
        BlockPos pos = event.getPos();
        BlockState state = event.getLevel().getBlockState(pos);
        Player player = event.getEntity();
        ItemStack heldItem = player.getMainHandItem();
        if (ItemStack.EMPTY.equals(heldItem)) {
            System.out.println("La mano esta vacia");
        }

        System.out.println("Datos del jugador: " + player.getMainHandItem());
        System.out.println(player.getEntityData());
    }

    @Override
    @SubscribeEvent
    public void onPlayerWalk(MovementInputUpdateEvent event) {
        if(event.getEntity() instanceof Player){
            if(event.getInput().down){
                System.out.println("down"+event.getInput().down);
            }
            if(event.getInput().up){
                System.out.println("up"+event.getInput().up);
            }
            if(event.getInput().right){
                System.out.println("right"+event.getInput().right);
            }
            if(event.getInput().left){
                System.out.println("left"+event.getInput().left);
            }
        }
    }

    @Override
    public long nextSubTickCount() {
        return 0;
    }

    @Override
    public LevelTickAccess<Block> getBlockTicks() {
        return null;
    }

    @Override
    public LevelTickAccess<Fluid> getFluidTicks() {
        return null;
    }

    @Override
    public LevelData getLevelData() {
        return null;
    }

    @Override
    public DifficultyInstance getCurrentDifficultyAt(BlockPos p_46800_) {
        return null;
    }

    @Nullable
    @Override
    public MinecraftServer getServer() {
        return null;
    }

    @Override
    public ChunkSource getChunkSource() {
        return null;
    }

    @Override
    public RandomSource getRandom() {
        return null;
    }

    @Override
    public void playSound(@Nullable Player p_46775_, BlockPos p_46776_, SoundEvent p_46777_, SoundSource p_46778_, float p_46779_, float p_46780_) {

    }

    @Override
    public void addParticle(ParticleOptions p_46783_, double p_46784_, double p_46785_, double p_46786_, double p_46787_, double p_46788_, double p_46789_) {

    }

    @Override
    public void levelEvent(@Nullable Player p_46771_, int p_46772_, BlockPos p_46773_, int p_46774_) {

    }

    @Override
    public void gameEvent(GameEvent p_220404_, Vec3 p_220405_, GameEvent.Context p_220406_) {

    }

    @Override
    public RegistryAccess registryAccess() {
        return null;
    }

    @Override
    public float getShade(Direction p_45522_, boolean p_45523_) {
        return 0;
    }

    @Override
    public LevelLightEngine getLightEngine() {
        return null;
    }

    @Override
    public WorldBorder getWorldBorder() {
        return null;
    }

    @Nullable
    @Override
    public BlockEntity getBlockEntity(BlockPos p_45570_) {
        return null;
    }

    @Override
    public BlockState getBlockState(BlockPos p_45571_) {
        return null;
    }

    @Override
    public FluidState getFluidState(BlockPos p_45569_) {
        return null;
    }

    @Override
    public List<Entity> getEntities(@Nullable Entity p_45936_, AABB p_45937_, Predicate<? super Entity> p_45938_) {
        return null;
    }

    @Override
    public <T extends Entity> List<T> getEntities(EntityTypeTest<Entity, T> p_151464_, AABB p_151465_, Predicate<? super T> p_151466_) {
        return null;
    }

    @Override
    public List<? extends Player> players() {
        return null;
    }

    @Nullable
    @Override
    public ChunkAccess getChunk(int p_46823_, int p_46824_, ChunkStatus p_46825_, boolean p_46826_) {
        return null;
    }

    @Override
    public int getHeight(Heightmap.Types p_46827_, int p_46828_, int p_46829_) {
        return 0;
    }

    @Override
    public int getSkyDarken() {
        return 0;
    }

    @Override
    public BiomeManager getBiomeManager() {
        return null;
    }

    @Override
    public Holder<Biome> getUncachedNoiseBiome(int p_204159_, int p_204160_, int p_204161_) {
        return null;
    }

    @Override
    public boolean isClientSide() {
        return false;
    }

    @Override
    public int getSeaLevel() {
        return 0;
    }

    @Override
    public DimensionType dimensionType() {
        return null;
    }

    @Override
    public boolean isStateAtPosition(BlockPos p_46938_, Predicate<BlockState> p_46939_) {
        return false;
    }

    @Override
    public boolean isFluidAtPosition(BlockPos p_151584_, Predicate<FluidState> p_151585_) {
        return false;
    }

    @Override
    public boolean setBlock(BlockPos p_46947_, BlockState p_46948_, int p_46949_, int p_46950_) {
        return false;
    }

    @Override
    public boolean removeBlock(BlockPos p_46951_, boolean p_46952_) {
        return false;
    }

    @Override
    public boolean destroyBlock(BlockPos p_46957_, boolean p_46958_, @Nullable Entity p_46959_, int p_46960_) {
        return false;
    }
}

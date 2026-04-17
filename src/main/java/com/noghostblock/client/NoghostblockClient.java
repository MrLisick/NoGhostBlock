package com.noghostblock.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class NoghostblockClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Регистрация команды /ghost для принудительного обновления области
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                    ClientCommandManager.literal("ghost").executes(context -> {
                        this.executeResync();
                        context.getSource().sendFeedback(Text.literal("§a[NoGhostBlock] Окружение синхронизировано!"));
                        return 1;
                    })
            );
        });
    }

    public void executeResync() {
        MinecraftClient mc = MinecraftClient.getInstance();
        ClientPlayNetworkHandler networkHandler = mc.getNetworkHandler();

        if (networkHandler == null || mc.player == null) return;

        BlockPos pos = mc.player.getBlockPos();

        // Синхронизация области 9x9x9 вокруг игрока
        for (int dx = -4; dx <= 4; dx++) {
            for (int dy = -4; dy <= 4; dy++) {
                for (int dz = -4; dz <= 4; dz++) {
                    requestBlockUpdate(pos.add(dx, dy, dz));
                }
            }
        }
    }

    public static void requestBlockUpdate(BlockPos pos) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.getNetworkHandler() != null) {
            client.getNetworkHandler().sendPacket(new PlayerActionC2SPacket(
                    PlayerActionC2SPacket.Action.ABORT_DESTROY_BLOCK, pos, Direction.DOWN
            ));
        }
    }
}
package com.iafenvoy.sop.registry;

import com.iafenvoy.sop.particle.SongEffectParticle;
import com.iafenvoy.sop.render.AggroSphereRenderer;
import com.iafenvoy.sop.render.SongCubeBlockEntityRenderer;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.particle.ParticleProviderRegistry;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;

public final class SopRenderers {
    public static void registerEntityRenderer() {
        EntityRendererRegistry.register(SopEntities.AGGRO_SPHERE, AggroSphereRenderer::new);
    }

    public static void registerParticleRenderer() {
        ParticleProviderRegistry.register(SopParticles.SONG_EFFECT, SongEffectParticle::create);
    }

    public static void registerBlockEntityRenderer() {
        BlockEntityRendererRegistry.register(SopBlockEntities.AGGRESSIUM_SONG_TYPE.get(), SongCubeBlockEntityRenderer.AggressiumSongCubeBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(SopBlockEntities.MOBILIUM_SONG_TYPE.get(), SongCubeBlockEntityRenderer.MobiliumSongCubeBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(SopBlockEntities.PROTISIUM_SONG_TYPE.get(), SongCubeBlockEntityRenderer.ProtisiumSongCubeBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(SopBlockEntities.SUPPORTIUM_SONG_TYPE.get(), SongCubeBlockEntityRenderer.SupportiumSongCubeBlockEntityRenderer::new);
    }
}

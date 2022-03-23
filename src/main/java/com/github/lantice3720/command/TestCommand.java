package com.github.lantice3720.command;

import com.destroystokyo.paper.ParticleBuilder;
import com.github.lantice3720.fx.Fx;
import com.github.lantice3720.fx.ParticleFx;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

/**
 * test 명령어 실행자
 *
 * @author lanthanide
 */
public class TestCommand implements CommandExecutor {

    /**
     * 플러그인
     */
    Plugin plugin;

    /**
     * 명령어 실행
     *
     * @param plugin
     */
    public TestCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // 사용자가 플레이어인지 체크
        if(sender instanceof Player player) {
            // 사용자가 플레이어
            List<Entity> blacklist = new ArrayList<>();
            test(50, player, blacklist);
            return true;
        } else {
                // 사용자가 플레이어가 아님
                return false;
        }
    }

    private void test(Integer count, Entity starter, List<Entity> blacklist) {
        if(count <= 0) {
            Bukkit.getLogger().info("Left count <=0. Simulation Ended.");
            return;
        }
        count -= 1;
        Integer finalCount = count;
        if(!blacklist.contains(starter)) blacklist.add(starter);
        new BukkitRunnable() {
            @Override
            public void run(){
                // 가장 가까운 엔티티
                Entity nearestEntity = Fx.getNearestEntity(starter.getLocation(), 5, 3, 5, blacklist);
                // 범위내에 엔티티 체크
                if(nearestEntity == null) {
                    Bukkit.getLogger().info("Cannot find Entity in 10x6x10 area. Simulation Ended.");
                    return;
                }

                // 위치 보정
                Location playerLocation = starter.getLocation();
                Location entityLocation = nearestEntity.getLocation();

                entityLocation.setY(entityLocation.getY() + (nearestEntity.getHeight() / 2));
                playerLocation.setY(playerLocation.getY() + (starter.getHeight() / 2));

                // 파티클 생성
                ParticleBuilder builder = new ParticleBuilder(Particle.REDSTONE);
                builder.color(Color.LIME).count(200).allPlayers();
                ParticleFx.drawLine(builder, playerLocation, entityLocation);

                // 발광효과 부여
                if(nearestEntity instanceof LivingEntity livingEntity) {
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 1, 1, false, false));
                }

                test(finalCount, nearestEntity, blacklist);
            }
        }.runTaskLater(plugin, 3);
    }
}

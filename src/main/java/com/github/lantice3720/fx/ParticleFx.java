package com.github.lantice3720.fx;

import com.destroystokyo.paper.ParticleBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.logging.Level;

/**
 * 파티클에 관련된 메서드를 모아둔 클래스
 */
public class ParticleFx {

    /**
     * 벡터값에 따라 파티클을 출력합니다.
     *
     * @param builder 파티클 정보
     * @param x 생성 x
     * @param y 생성 y
     * @param z 생성 z
     * @param vector 생성좌표로부터의 기울기
     * @param world 소환할 월드
     */
    public static void drawLine(ParticleBuilder builder, Double x, Double y, Double z, Vector vector, World world) {
        Bukkit.getLogger().info("Vecter is pointing " + (x + vector.getX()) + ", " +  (y + vector.getY()) + ", " + (z + vector.getZ()));
        if(builder.count() == 1) {
            // 중앙에 파티클 출력
            vector.multiply(0.5);
            world.spawnParticle(builder.particle(), x + vector.getX(), y + vector.getY(), z + vector.getZ(), 1, builder.data());
        } else if(builder.count() > 1){
            // 선형 파티클 출력
            double particleUnit = vector.length() / builder.count();

            // 생성 좌표부터 시작해서 일렬로 파티클을 생성
            Vector particleVecter = vector.clone();
            for(double i = particleUnit; i < vector.length(); i += particleUnit) {
                particleVecter.normalize();
                particleVecter.multiply(i);
                world.spawnParticle(builder.particle(), x + particleVecter.getX(), y + particleVecter.getY(), z + particleVecter.getZ(), 1, builder.data());
            }
        } else {
            // 파티클 개수 에러
            Bukkit.getLogger().log(Level.SEVERE, "[LanRPG] Cannot draw line with 0 or less particles!");
        }
    }

    /**
     * 두 위치를 받아 파티클을 출력합니다.
     *
     * @param builder 파티클 정보
     * @param from 시작위치
     * @param to 끝위치
     */
    public static void drawLine(ParticleBuilder builder, Location from, Location to) {
        // 월드 에러 감지
        if(!from.getWorld().equals(to.getWorld())) {
            Bukkit.getLogger().log(Level.SEVERE, "[LanRPG] Cannot create particle between other worlds!");
            return;
        }

        drawLine(builder, from.getX(), from.getY(), from.getZ(), Fx.locationsVector(from, to), from.getWorld());
    }

//    /**
//     * 주어진 좌표와 크기를 바탕으로 파티클을 구형으로 출력합니다.
//     *
//     * @param builder 파티클 정보
//     * @param x 생성 x
//     * @param y 생성 y
//     * @param z 생성 z
//     * @param r 구 반지를
//     * @param world 소환할 월드
//     * @param frequency 0초과 1이하. 겉넓이 1당 파티클 수를 의미
//     * @param hollow 구 내부 비움여부
//     */
//    public static void drawSphere(ParticleBuilder builder, Double x, Double y, Double z, Double r, World world, Float frequency, Boolean hollow) {
//        // 벡터에 들어갈 값
//        double dX, dY, dZ;
//        for(dX = 0; dX < r; dX += 0.1) {
//
//        }
//    }
}

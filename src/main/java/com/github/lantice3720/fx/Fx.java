package com.github.lantice3720.fx;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 여러가지 메서드들을 모아둔 클래스
 */
public class Fx {

    /**
     * 위치에서 범위 내에 가장 가까운 엔티티를 찾습니다.
     *
     * @param location
     * @param x 범위설정 x
     * @param y 범위설정 y
     * @param z 범위설정 z
     * @param blacklist 제외할 엔티티 목록
     * @return 가장 가까운 엔티티
     */
    public static Entity getNearestEntity(Location location, Integer x, Integer y, Integer z, List<Entity> blacklist) {
        // 변수 셋팅
        Collection<Entity> nearByEntities = location.getNearbyEntities(x, y, z);
        Entity nearestEntity = null;
        double nearestDistance = Double.MAX_VALUE;

        // 가장 가까운 엔티티 찾기
        for (Entity entity : nearByEntities) {
            if (blacklist.contains(entity)) {
                continue;
            }

            double distance = entity.getLocation().distance(location);

            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearestEntity = entity;
            }
        }

        return nearestEntity;
    }

    /**
     * 위치에서 범위 내에 가장 가까운 엔티티를 찾습니다.
     *
     * @param location 위치
     * @param x 범위설정 x
     * @param y 범위설정 y
     * @param z 범위설정 z
     * @param black 제외할 엔티티
     * @return 가장 가까운 엔티티
     */
    public static Entity getNearestEntity(Location location, Integer x, Integer y, Integer z, Entity black) {
        // 엔티티 리스트화
        List<Entity> blacklist = List.of(black);
        return getNearestEntity(location, x, y, z, blacklist);
    }

    /**
     * 두 위치를 받아 상대적 벡터로 바꿉니다.
     *
     * @param from 기준좌표
     * @param to 기울기 구할 좌표
     * @return 벡터
     */
    public static Vector locationsVector(Location from, Location to) {
        Vector fromV = new Vector(from.getX(), from.getY(), from.getZ());
        Vector toV = new Vector(to.getX(), to.getY(), to.getZ());

        return toV.subtract(fromV);
    }

    /**
     * 구의 반지름을 받아 구 표면의 점들을 구합니다. 빈도가 낮을수록 많은 점을 구합니다.
     *
     * @param r 반지름
     * @param frequency 빈도
     * @return (x, y, z)순서쌍의 리스트
     */
    public static ArrayList<ArrayList<Double>> makeSphere(Double r, Float frequency) {


        return new ArrayList<>();
    }

}

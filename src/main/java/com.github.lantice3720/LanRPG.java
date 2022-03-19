package com.github.lantice3720;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class LanRPG extends JavaPlugin {

    // 플러그인 로드
    @Override
    public void onEnable() {
        // 로딩 시작메시지
        Bukkit.getLogger().info("Starting to load LanRPG");

        // 로딩 완료메시지
        Bukkit.getLogger().info("Finished loading LanRPG");
    }
}

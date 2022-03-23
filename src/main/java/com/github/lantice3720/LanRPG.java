package com.github.lantice3720;

import com.github.lantice3720.command.TestCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 플러그인 메인 클래스
 */
public class LanRPG extends JavaPlugin {

    // 플러그인 로드
    @Override
    public void onEnable() {
        // 로딩 시작메시지
        Bukkit.getLogger().info("Starting to load LanRPG");

        // 명령어 등록
        getCommand("test").setExecutor(new TestCommand(this));

        // 로딩 완료메시지
        Bukkit.getLogger().info("Finished loading LanRPG");
    }
}

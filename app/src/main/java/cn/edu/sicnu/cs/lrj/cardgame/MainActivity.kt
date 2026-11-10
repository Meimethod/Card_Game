package cn.edu.sicnu.cs.lrj.cardgame

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //加载菜单
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    //菜单点击事件
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_about){
            //弹出关于对话框
            AlertDialog.Builder(this)
                .setTitle("关于")
                .setMessage("翻牌卡牌游戏\nMVC架构，RecyclerView实现\n作者：XXX 学号：XXX")
                .setPositiveButton("确定",null)
                .show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

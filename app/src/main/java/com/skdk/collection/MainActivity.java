package com.skdk.collection;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
    private final int blue = Color.rgb(13, 66, 190);
    private final int yellow = Color.rgb(255, 197, 0);
    private LinearLayout content;
    private String page = "Home";

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(blue);
        getWindow().setNavigationBarColor(Color.WHITE);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        render();
    }
    private TextView text(String value, int size, int color, boolean bold) {
        TextView t = new TextView(this); t.setText(value); t.setTextSize(size); t.setTextColor(color);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD); return t;
    }
    private LinearLayout column() {
        LinearLayout l = new LinearLayout(this); l.setOrientation(LinearLayout.VERTICAL); l.setPadding(16,12,16,12); return l;
    }
    private void render() {
        LinearLayout root = new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setBackgroundColor(Color.rgb(246,248,252));
        LinearLayout header = new LinearLayout(this); header.setOrientation(LinearLayout.VERTICAL); header.setPadding(18,16,18,14); header.setBackgroundColor(blue);
        header.addView(text("SK&DK COLLECTION",23,Color.WHITE,true));
        header.addView(text("Best Products  •  Best Prices  •  For You",12,Color.WHITE,false));
        EditText search = new EditText(this); search.setSingleLine(true); search.setTextSize(14); search.setHint("Search products, brands and more...");
        LinearLayout.LayoutParams sp = new LinearLayout.LayoutParams(-1,48); sp.topMargin=12; header.addView(search,sp); root.addView(header);
        ScrollView scroll = new ScrollView(this); content = column();
        if (page.equals("Home")) {
            TextView banner=text("BIG SAVINGS EVERY DAY\nUp to 70% OFF",22,Color.WHITE,true); banner.setGravity(Gravity.CENTER_VERTICAL); banner.setPadding(18,20,18,20); banner.setBackgroundColor(Color.rgb(22,48,112)); content.addView(banner);
            addHeading("Shop by Category"); addCategoryRow("📱 Mobiles","👕 Fashion","💻 Electronics"); addCategoryRow("🏠 Home","💄 Beauty","⚽ Sports");
            addHeading("Deals of the Day"); addProduct("Wireless Headphones","₹799","40% OFF"); addProduct("Men's Casual T-Shirt","₹499","NEW"); addProduct("Sports Shoes","₹1,299","25% OFF");
        } else if (page.equals("Categories")) {
            addHeading("All Categories"); for(String s:new String[]{"Mobiles & Accessories","Fashion","Electronics","Home & Living","Beauty & Personal Care","Appliances","Sports & Fitness","Toys & Games","Books & More"}) addCategoryRow("🛍️ "+s,"›","");
        } else if (page.equals("Cart")) {
            addHeading("My Cart"); addProduct("Men's Casual T-Shirt","₹499","Qty: 1"); addProduct("Sports Shoes","₹1,299","Qty: 1"); addHeading("Total: ₹1,798");
            Button buy=new Button(this); buy.setText("Proceed to Buy"); buy.setBackgroundTintList(android.content.res.ColorStateList.valueOf(yellow)); content.addView(buy);
        } else {
            addHeading("Welcome to SK&DK COLLECTION"); addCategoryRow("📦 My Orders","♡ Wishlist","🎟 Coupons"); addCategoryRow("👤 Profile Information","📍 Address Book","⚙ Settings");
            content.addView(text("Your account and order history will be available here.",14,Color.DKGRAY,false));
        }
        scroll.addView(content); root.addView(scroll,new LinearLayout.LayoutParams(-1,0,1));
        LinearLayout nav=new LinearLayout(this); nav.setBackgroundColor(Color.WHITE);
        for(String tab:new String[]{"Home","Categories","Cart","Account"}) {
            TextView b=text(tab,12,page.equals(tab)?blue:Color.DKGRAY,true); b.setGravity(Gravity.CENTER); b.setPadding(4,14,4,14);
            nav.addView(b,new LinearLayout.LayoutParams(0,-2,1)); b.setOnClickListener(v->{page=tab;render();});
        }
        root.addView(nav); setContentView(root);
    }
    private void addHeading(String title) {
        TextView t=text(title,18,Color.rgb(25,35,55),true); LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,-2); p.topMargin=16; p.bottomMargin=8; content.addView(t,p);
    }
    private void addCategoryRow(String a,String b,String c) {
        LinearLayout row=new LinearLayout(this); row.setGravity(Gravity.CENTER_VERTICAL);
        for(String s:new String[]{a,b,c}) if(!s.isEmpty()) {
            TextView t=text(s,13,blue,true); t.setGravity(Gravity.CENTER); t.setPadding(8,14,8,14); t.setBackgroundColor(Color.WHITE);
            LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(0,-2,1); p.setMargins(3,3,3,3); row.addView(t,p);
        } content.addView(row);
    }
    private void addProduct(String name,String price,String offer) {
        LinearLayout card=new LinearLayout(this); card.setOrientation(LinearLayout.VERTICAL); card.setPadding(14,12,14,12); card.setBackgroundColor(Color.WHITE);
        card.addView(text("🛍️",28,blue,false)); card.addView(text(name,16,Color.DKGRAY,true));
        LinearLayout row=new LinearLayout(this); row.addView(text(price+"   ",17,Color.BLACK,true)); row.addView(text(offer,12,Color.rgb(0,130,70),true)); card.addView(row);
        LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,-2); p.setMargins(0,6,0,6); content.addView(card,p);
    }
}

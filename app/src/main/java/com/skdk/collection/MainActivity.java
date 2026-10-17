package com.skdk.collection;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
    private final int blue=Color.rgb(18,66,190), deepBlue=Color.rgb(7,42,135), yellow=Color.rgb(255,204,0);
    private LinearLayout content;
    private String page="Home";
    private int cartCount=0;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(deepBlue);
        getWindow().setNavigationBarColor(Color.WHITE);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        render();
    }
    private TextView txt(String s,int size,int color,boolean bold) {
        TextView t=new TextView(this); t.setText(s); t.setTextSize(size); t.setTextColor(color);
        if(bold)t.setTypeface(Typeface.DEFAULT,Typeface.BOLD); return t;
    }
    private GradientDrawable bg(int color,int radius) {
        GradientDrawable d=new GradientDrawable(); d.setColor(color); d.setCornerRadius(radius); return d;
    }
    private LinearLayout vertical(int pad) {
        LinearLayout l=new LinearLayout(this); l.setOrientation(LinearLayout.VERTICAL); l.setPadding(pad,pad,pad,pad); return l;
    }
    private void render() {
        LinearLayout root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setBackgroundColor(Color.rgb(245,247,250));
        LinearLayout header=vertical(12); header.setPadding(14,12,14,12); header.setBackgroundColor(blue);
        LinearLayout top=new LinearLayout(this); top.setGravity(Gravity.CENTER_VERTICAL);
        ImageView logo=new ImageView(this); logo.setImageResource(com.skdk.collection.R.drawable.skdk_logo);
        top.addView(logo,new LinearLayout.LayoutParams(44,44));
        LinearLayout brand=new LinearLayout(this); brand.setOrientation(LinearLayout.VERTICAL); brand.setPadding(9,0,0,0);
        brand.addView(txt("SK&DK COLLECTION",18,Color.WHITE,true)); brand.addView(txt("Shop More. Live Better.",10,Color.WHITE,false));
        top.addView(brand,new LinearLayout.LayoutParams(0,-2,1));
        TextView cart=txt("🛒",24,Color.WHITE,true); top.addView(cart); cart.setOnClickListener(v->{page="Cart";render();});
        header.addView(top);
        EditText search=new EditText(this); search.setSingleLine(true); search.setTextSize(14); search.setHint("Search for products, brands and more");
        search.setCompoundDrawablesWithIntrinsicBounds(android.R.drawable.ic_menu_search,0,0,0); search.setPadding(12,0,12,0); search.setBackground(bg(Color.WHITE,18));
        LinearLayout.LayoutParams ep=new LinearLayout.LayoutParams(-1,44); ep.topMargin=10; header.addView(search,ep); root.addView(header);
        ScrollView scroll=new ScrollView(this); content=vertical(12);
        if(page.equals("Home")) {
            LinearLayout banner=vertical(14); banner.setBackground(bg(deepBlue,18));
            banner.addView(txt("BIG SAVINGS EVERY DAY",21,Color.WHITE,true)); banner.addView(txt("Fashion • Electronics • Home",13,Color.WHITE,false));
            TextView offer=txt("UP TO 70% OFF     Shop Now  →",14,Color.BLACK,true); offer.setGravity(Gravity.CENTER); offer.setPadding(8,10,8,10); offer.setBackground(bg(yellow,10));
            LinearLayout.LayoutParams op=new LinearLayout.LayoutParams(-1,-2); op.topMargin=10; banner.addView(offer,op); content.addView(banner);
            addHeading("Shop by Category", "VIEW ALL");
            LinearLayout cats=new LinearLayout(this); cats.setGravity(Gravity.CENTER);
            addCategory(cats,"📱","Mobiles"); addCategory(cats,"👕","Fashion"); addCategory(cats,"💻","Electronics"); addCategory(cats,"🏠","Home");
            content.addView(cats);
            addHeading("Deals of the Day","VIEW ALL");
            addProduct("Wireless Headphones","₹799","40% off","🎧");
            addProduct("Men's Casual T-Shirt","₹499","New arrival","👕");
            addProduct("Sports Shoes","₹1,299","25% off","👟");
            addProduct("Smart Watch","₹999","Best seller","⌚");
        } else if(page.equals("Categories")) {
            addHeading("All Categories","");
            for(String s:new String[]{"Mobiles & Accessories","Fashion","Electronics","Home & Living","Beauty & Personal Care","Appliances","Sports & Fitness","Toys & Games","Books & More"}) {
                TextView row=txt("  🛍️     "+s+"                                  ›",15,Color.DKGRAY,true); row.setPadding(10,16,8,16); row.setBackground(bg(Color.WHITE,12));
                LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,-2); p.bottomMargin=7; content.addView(row,p);
            }
        } else if(page.equals("Cart")) {
            addHeading("My Cart","");
            if(cartCount==0) content.addView(txt("Your cart is ready for shopping. Add products from Home.",15,Color.DKGRAY,false));
            else addProduct("Selected demo product","₹799","Qty: "+cartCount,"🛍️");
            Button buy=new Button(this); buy.setText("PROCEED TO BUY"); buy.setTextColor(Color.BLACK); buy.setBackgroundTintList(android.content.res.ColorStateList.valueOf(yellow)); content.addView(buy);
        } else {
            addHeading("Welcome to SK&DK COLLECTION","");
            for(String s:new String[]{"📦   My Orders","♡   Wishlist","🎟   Coupons","👤   Profile Information","📍   Address Book","⚙   Settings","❓   Help & Support"}) {
                TextView row=txt(s+"     ›",15,Color.DKGRAY,true); row.setPadding(12,16,12,16); row.setBackground(bg(Color.WHITE,12));
                LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,-2); p.bottomMargin=7; content.addView(row,p);
            }
        }
        scroll.addView(content); root.addView(scroll,new LinearLayout.LayoutParams(-1,0,1));
        LinearLayout nav=new LinearLayout(this); nav.setBackgroundColor(Color.WHITE);
        String[] tabs={"Home","Categories","Cart","Account"};
        for(String tab:tabs) {
            String symbol=tab.equals("Home")?"⌂":tab.equals("Categories")?"▦":tab.equals("Cart")?"🛒":"♙";
            LinearLayout item=new LinearLayout(this); item.setOrientation(LinearLayout.VERTICAL); item.setGravity(Gravity.CENTER); item.setPadding(3,8,3,8);
            item.addView(txt(symbol,19,page.equals(tab)?blue:Color.GRAY,true));
            item.addView(txt(tab,11,page.equals(tab)?blue:Color.GRAY,page.equals(tab)));
            nav.addView(item,new LinearLayout.LayoutParams(0,-2,1)); item.setOnClickListener(v->{page=tab;render();});
        }
        root.addView(nav); setContentView(root);
    }
    private void addHeading(String s,String action) {
        LinearLayout row=new LinearLayout(this); row.setGravity(Gravity.CENTER_VERTICAL);
        row.addView(txt(s,18,Color.rgb(30,40,58),true),new LinearLayout.LayoutParams(0,-2,1));
        if(!action.isEmpty())row.addView(txt(action,11,blue,true));
        content.addView(row);
    }
    private void addCategory(LinearLayout row,String emoji,String label) {
        LinearLayout item=new LinearLayout(this); item.setOrientation(LinearLayout.VERTICAL); item.setGravity(Gravity.CENTER); item.setPadding(4,10,4,10);
        TextView icon=txt(emoji,25,blue,true); icon.setGravity(Gravity.CENTER); icon.setBackground(bg(Color.WHITE,30));
        item.addView(icon,new LinearLayout.LayoutParams(54,54)); TextView name=txt(label,10,Color.DKGRAY,true); name.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams np=new LinearLayout.LayoutParams(-1,-2); np.topMargin=5; item.addView(name,np);
        row.addView(item,new LinearLayout.LayoutParams(0,-2,1));
    }
    private void addProduct(String name,String price,String offer,String emoji) {
        LinearLayout card=vertical(10); card.setBackground(bg(Color.WHITE,14));
        LinearLayout top=new LinearLayout(this); top.setGravity(Gravity.CENTER_VERTICAL);
        TextView picture=txt(emoji,42,blue,true); picture.setGravity(Gravity.CENTER); picture.setBackground(bg(Color.rgb(242,246,255),12));
        top.addView(picture,new LinearLayout.LayoutParams(82,82));
        LinearLayout details=vertical(3); details.setPadding(12,0,0,0);
        details.addView(txt(name,15,Color.rgb(35,40,50),true)); details.addView(txt("★ 4.3  •  Free delivery",11,Color.rgb(0,135,75),false));
        LinearLayout priceRow=new LinearLayout(this); priceRow.setGravity(Gravity.CENTER_VERTICAL);
        priceRow.addView(txt(price+"  ",17,Color.BLACK,true)); priceRow.addView(txt(offer,11,Color.rgb(0,135,75),true)); details.addView(priceRow);
        top.addView(details,new LinearLayout.LayoutParams(0,-2,1)); card.addView(top);
        Button add=new Button(this); add.setText("ADD TO CART"); add.setTextSize(12); add.setTextColor(Color.BLACK);
        add.setBackgroundTintList(android.content.res.ColorStateList.valueOf(yellow)); add.setOnClickListener(v->{cartCount++;Toast.makeText(this,"Added to cart (demo)",Toast.LENGTH_SHORT).show();});
        card.addView(add); LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,-2); p.bottomMargin=8; content.addView(card,p);
    }
}
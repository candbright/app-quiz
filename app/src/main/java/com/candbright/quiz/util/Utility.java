package com.candbright.quiz.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.candbright.quiz.app.GlobalApp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>created by wyh in 2021/12/14</p>
 */
public class Utility {

    private static final String TAG = Utility.class.getSimpleName();

    public static SharedPreferences getSharedPreferences(Context context) {
        if (null == context) {
            L.e(TAG, "(getSharedPreferences failed, context is null.");
            return null;
        }
        return context.getSharedPreferences("share_" + "", Context.MODE_PRIVATE);
    }

    public static void setShareFileData(Context context, String dataKey, Object data) {
        try {

            SharedPreferences sharedPreferences = Utility.getSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPreferences.edit();


            if (data instanceof Boolean) {
                // bool
                editor.putBoolean(dataKey, (Boolean) data);
            } else if (data instanceof Integer) {
                // int
                editor.putInt(dataKey, (int) data);
            } else if (data instanceof Long) {
                // long
                editor.putLong(dataKey, (long) data);
            } else if (data instanceof Float) {
                // float
                editor.putFloat(dataKey, (Float) data);
            } else if (data instanceof String) {
                // string
                editor.putString(dataKey, (String) data);
            } else if (data instanceof List) {
                Gson gson = new Gson();
                String strJson = gson.toJson(data);
                editor.putString(dataKey, strJson);
            } else {
                L.e(TAG, "getSharedPreferences failed, error object type.");
            }
            editor.commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static Object getShareFileData(Context context, String dataKey, Object defaultData) {

        Object data = defaultData;
        if (null == dataKey || null == defaultData) {
            return data;
        }
        try {

            SharedPreferences sharedPreferences = Utility.getSharedPreferences(context);

            if (defaultData instanceof Boolean) {
                // bool
                data = sharedPreferences.getBoolean(dataKey, (Boolean) defaultData);
            } else if (defaultData instanceof Integer) {
                // int
                data = sharedPreferences.getInt(dataKey, (int) defaultData);
            } else if (defaultData instanceof Long) {
                // long
                data = sharedPreferences.getLong(dataKey, (long) defaultData);
            } else if (defaultData instanceof Float) {
                // float
                data = sharedPreferences.getFloat(dataKey, (Float) defaultData);
            } else if (defaultData instanceof String) {
                // string
                data = sharedPreferences.getString(dataKey, (String) defaultData);
            } else if (defaultData instanceof List) {
                String strJson = sharedPreferences.getString(dataKey, null);
                if (null == strJson) {
                    return defaultData;
                }
                Gson gson = new Gson();
                data = gson.fromJson(strJson, new TypeToken<List<String>>() {
                }.getType());
            } else {
                L.e(TAG, "getSharedPreferences failed, error object type.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static <T> String beanToJson(T bean) {
        if (null == bean) {
            return "";
        }
        try {
            Type type = new TypeToken<T>() {
            }.getType();
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            String json = gson.toJson(bean, type);
            Log.d(bean.getClass().toString(), "beanToJson: " + json);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static <T> List<T> jsonToList(String json, Class<T> classOfT) {
        try {
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = parser.parse(json).getAsJsonArray();
            Gson gson = new Gson();
            ArrayList<T> list = new ArrayList<>();
            for (JsonElement user : jsonArray) {

                //使用GSON，直接转成Bean对象

                T bean = (T) gson.fromJson(user, classOfT);

                list.add(bean);

            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * <p>将本地json文件转为String对象</p>
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * <p>根据资源名找到对应的资源id。</p>
     */
    public static int getResId(String completeName) {
        String[] splitName = completeName.split("\\.");
        if (splitName.length == 3) {
            return getResId(splitName[2], splitName[1]);
        }
        return -1;
    }

    /**
     * <p>根据资源名字和资源类型找到对应的资源id。
     * 例如找R.drawable.image，则name = image，type = R.drawable。</p>
     *
     * @param name 资源文件名
     * @param type 资源类型
     * @return 资源id
     */
    public static int getResId(String name, String type) {
        try {
            Resources res = GlobalApp.getInstance().getResources();
            int i = res.getIdentifier(name, type, GlobalApp.getInstance().getPackageName());
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int getColor(int id) {
        return ContextCompat.getColor(GlobalApp.getInstance(), id);
    }

    public static String getString(String resourceKey) {
        return getString(getResId(resourceKey, "string"));
    }

    public static String getString(int resourceId) {
        return GlobalApp.getInstance().getResources().getString(resourceId);
    }

    public static float dip2px(float dpValue) {
        final float scale = GlobalApp.getInstance().getApplicationContext().getResources().getDisplayMetrics().density;
        return dpValue * scale + 0.5f;
    }

    public static float px2dip(float pxValue) {
        final float scale = GlobalApp.getInstance().getApplicationContext().getResources().getDisplayMetrics().density;
        return pxValue / scale + 0.5f;
    }

    public static int px2sp(float pxValue) {
        final float fontScale = GlobalApp.getInstance().getApplicationContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int sp2px(float dpValue) {
        final float fontScale = GlobalApp.getInstance().getApplicationContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (dpValue * fontScale + 0.5f);
    }

    public static int dip2sp(float dpValue) {
        final float scale = GlobalApp.getInstance().getApplicationContext().getResources().getDisplayMetrics().density;
        final float fontScale = GlobalApp.getInstance().getApplicationContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) ((dpValue * scale + 0.5f) / fontScale + 0.5f);
    }
}

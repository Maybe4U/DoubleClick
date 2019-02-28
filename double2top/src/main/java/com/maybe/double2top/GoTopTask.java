package com.maybe.double2top;

/*===================新增====================*/

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * method desc.  : 传递当前RecyclerView的Item值，回滚到顶部
 *                 修改休眠值可以改变滚动时间
 * params        :
 * return        :
 */
public class GoTopTask extends AsyncTask<Integer, Integer, String> {
    private RecyclerView recyclerView;
    private static final int STARTPOSITION = 10;//开始滑动的位置

    public GoTopTask(View view) {
        recyclerView = (RecyclerView) view;
    }

    @Override
    protected void onPreExecute() {
        //回到顶部时间置0  此处的时间不是狭义上的时间
        super.onPreExecute();
    }
    @Override
    protected String doInBackground(Integer... params) {
        // TODO Auto-generated method stub

        for(int i=params[0];i>=0;i--){

            //返回顶部时间耗费15个item还没回去，则直接去顶部
            //目的：要产生滚动的假象，但也不能耗时过多
            if(i > STARTPOSITION){
                publishProgress(STARTPOSITION);
                i = STARTPOSITION;
            }else {
                publishProgress(i);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    public void onProgressUpdate(Integer... values) {
        //recyclerView.smoothScrollToPosition(values[0]);
        if(values[0] == STARTPOSITION){
            recyclerView.scrollToPosition(values[0]);
        }else {
            recyclerView.smoothScrollToPosition(values[0]);
        }
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
    @Override
    protected void onCancelled() {
        // TODO Auto-generated method stub
        super.onCancelled();
    }

}

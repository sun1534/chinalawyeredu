package com.cqmm.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;

import com.cqmm.common.CurSession;
import com.cqmm.common.DataService;
import com.cqmm.common.SysParams;



public class CommandList extends TabActivity {

	static int deviceid;
	static String devicename;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final TabHost tabHost = getTabHost();
		
		Bundle bundle=getIntent().getExtras();
		deviceid=bundle.getInt("deviceid");
		devicename=bundle.getString("devicename");
		setTitle(SysParams.SYS_NAME + "(" + CurSession.username + ")设备维护-"+devicename);
	
		
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("标准维护命令")
				.setContent(new Intent(this, CommandStandard.class)));

		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("自定义命令")
				.setContent(
						new Intent(this, CommandCustomer.class)
								.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("设备应急操作")
				.setContent(
						new Intent(this, CommandUrgent.class)
								.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
	}
}

//
//public class CommandList extends Activity {
//	int deviceid;
//	String devicename;
//	
//	private Handler handler = new Handler(){
//		@Override
//		public void handleMessage(Message msg) {
//			// TODO Auto-generated method stub
//			super.handleMessage(msg);
//			Bundle b = msg.getData();
//			if(b.getString("result").equals("true")){
//				Log.v("aaaaaa", "oooooooooo");
//				initialData();
//		        mAdapter = new InfoDetailsAdapter(CommandList.this);
//		        
//		        expandList.setAdapter(mAdapter);
//		        
//		        expandList.setOnGroupClickListener(new OnGroupClickListener(){
//
//					@Override
//					public boolean onGroupClick(ExpandableListView arg0, View arg1,
//							int arg2, long arg3) {
//						// TODO Auto-generated method stub
//						Toast.makeText(activity,"[Group Click]:"+arg2,Toast.LENGTH_LONG).show();
//						
//						return false;
//					}
//		        	
//		        });
//		        
//		        expandList.setOnChildClickListener(new OnChildClickListener(){
//
//					@Override
//					public boolean onChildClick(ExpandableListView arg0, View arg1,
//							int arg2, int arg3, long arg4) {
//						// TODO Auto-generated method stub
//						Toast.makeText(activity,"[Child Click]:"+arg2+":"+arg3,Toast.LENGTH_LONG).show();
//						
//						return false;
//					}
//		        	
//		        });
//			}
//		}
//	};
//
//	
//	Activity activity;
//    
//	List<String> group;
//    List<List<String>> child;
//    
//    ExpandableListView expandList;
//    InfoDetailsAdapter mAdapter;
//    
//    Dialog dialogAdd;
//    Dialog dialogDelete;
//    
//    View viewAdd;
//    View viewDelete;
//    
//    OnClickListener clickListener;
//    
//    EditText add_name;
//    EditText add_phone;
//    EditText add_sex;
//    EditText add_home;
//    Button add_ok,add_no;
//    
//    EditText delete_id;
//    Button delete_ok,delete_no;
//    
//	/** Called when the activity is first created. */
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.command_list);
//        activity = this;
//
//        expandList = (ExpandableListView)findViewById(R.id.commandList);
//        
//        
//        
//		 Bundle bundle=getIntent().getExtras();
//		 deviceid=bundle.getInt("deviceid");
//		 devicename=bundle.getString("devicename");
//		 setTitle(SysParams.SYS_NAME + "(" + CurSession.username + ")设备维护-"+devicename);
//		 setContentView(R.layout.command_list);
//		new Thread(){
//			@Override
//			public void run() {
//				List devices=DataService.getDevice();
//				if(devices.size()>0){
//					Message msg = new Message();
//		            Bundle b = new Bundle();// 存放数据
//		            b.putString("result", "true");
//		            msg.setData(b);
//
//		            CommandList.this.handler.sendMessage(msg); // 向Handler发送消息,更新UI
//				}
//				
//			}
//		}.start();
//		
//    }
//    
//    public void initialData(){
//    	group = new ArrayList<String>();
//    	
//    	child = new ArrayList<List<String>>();
//    	
//    	addInfo("标准维护命令", new String[]{"13776117119","man","Jiangsu"});
//    	addInfo("自定义命令",new String[]{"1321134","man","Taiwan"});
//    	addInfo("设备应急操作",new String[]{"12345"});
//    }
//    
//    public void addInfo(String p,String[] c){
//    	group.add(p);
//    	
//    	List<String> item = new ArrayList<String>();
//    	
//    	for(int i=0;i<c.length;i++){
//    		item.add(c[i]);
//    	}
//    	
//    	child.add(item);
//    }
//    
////	public boolean onCreateOptionsMenu(Menu menu) {
////		menu.add(0, MENU_ADD, 0, "新增");
////		menu.add(0, MENU_DELETE, 0, "删除");
////		
////		return true;
////	}
////	
////	public boolean onOptionsItemSelected(MenuItem item) {
////    	switch (item.getItemId()) { 
////    	case MENU_ADD:
////    		dialogAdd.show();
////    		break;
////    	
////    	case MENU_DELETE:
////    		dialogDelete.show();
////    		break;
////    	}
////    	return false; 
////    }
//	class InfoDetailsAdapter extends BaseExpandableListAdapter {
//    	Activity activity;
//    	
//    	public InfoDetailsAdapter(Activity a){
//    		activity = a;
//    	}
//    	
//    	//child method stub
//    	
//		@Override
//		public Object getChild(int groupPosition, int childPosition) {
//			// TODO Auto-generated method stub
//			return child.get(groupPosition).get(childPosition);
//		}
//
//		@Override
//		public long getChildId(int groupPosition, int childPosition) {
//			// TODO Auto-generated method stub
//			return childPosition;
//		}
//
//		@Override
//		public int getChildrenCount(int groupPosition) {
//			// TODO Auto-generated method stub
//			return child.get(groupPosition).size();
//		}
//		
//		@Override
//		public View getChildView(int groupPosition, int childPosition,
//				boolean isLastChild, View convertView, ViewGroup parent) {
//			// TODO Auto-generated method stub
//			String string = child.get(groupPosition).get(childPosition);
//			return getGenericView(string);
//		}
//
//
//		//group method stub
//		@Override
//		public Object getGroup(int groupPosition) {
//			// TODO Auto-generated method stub
//			return group.get(groupPosition);
//		}
//
//		@Override
//		public int getGroupCount() {
//			// TODO Auto-generated method stub
//			return group.size();
//		}
//
//		@Override
//		public long getGroupId(int groupPosition) {
//			// TODO Auto-generated method stub
//			return groupPosition;
//		}
//
//		@Override
//		public View getGroupView(int groupPosition, boolean isExpanded,
//				View convertView, ViewGroup parent) {
//			// TODO Auto-generated method stub
//			String string = group.get(groupPosition);
//			return getGenericView(string);
//		}
//
//		//View stub to create Group/Children 's View
//		public TextView getGenericView(String s) {
//            // Layout parameters for the ExpandableListView
//            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
//                    ViewGroup.LayoutParams.FILL_PARENT, 64);
//
//            TextView text = new TextView(activity);
//            text.setLayoutParams(lp);
//            // Center the text vertically
//            text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
//            // Set the text starting position
//            text.setPadding(36, 0, 0, 0);
//            
//            text.setText(s);
//            return text;
//        }
//		
//		
//		
//		@Override
//		public boolean hasStableIds() {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//		@Override
//		public boolean isChildSelectable(int groupPosition, int childPosition) {
//			// TODO Auto-generated method stub
//			return true;
//		}
//    	
//    	
//    }
//}
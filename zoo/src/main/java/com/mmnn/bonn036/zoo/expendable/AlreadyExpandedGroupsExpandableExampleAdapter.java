/*
 *    Copyright (C) 2015 Haruki Hasegawa
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.mmnn.bonn036.zoo.expendable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemConstants;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;
import com.mmnn.bonn036.zoo.R;
import com.mmnn.bonn036.zoo.view.widget.ExpandableItemIndicator;

class AlreadyExpandedGroupsExpandableExampleAdapter
        extends AbstractExpandableItemAdapter<AlreadyExpandedGroupsExpandableExampleAdapter.MyGroupViewHolder, AlreadyExpandedGroupsExpandableExampleAdapter.MyChildViewHolder> {
    private static final String TAG = "MyExpandableItemAdapter";

    private RecyclerViewExpandableItemManager mExpandableItemManager;

    public AlreadyExpandedGroupsExpandableExampleAdapter(
            RecyclerViewExpandableItemManager expandableItemManager) {

        mExpandableItemManager = expandableItemManager;
        // ExpandableItemAdapter requires stable ID, and also
        // have to implement the getGroupItemId()/getChildItemId() methods appropriately.
        setHasStableIds(true);
    }

    @Override
    public int getGroupCount() {
        return 5;
    }

    @Override
    public int getChildCount(int groupPosition) {
        return 3;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition*10+childPosition;
    }

    @Override
    public int getGroupItemViewType(int groupPosition) {
        return 0;
    }

    @Override
    public int getChildItemViewType(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public MyGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View v = inflater.inflate(R.layout.list_group_item, parent, false);
        return new MyGroupViewHolder(v);
    }

    @Override
    public MyChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View v = inflater.inflate(R.layout.list_item, parent, false);
        return new MyChildViewHolder(v);
    }

    @Override
    public void onBindGroupViewHolder(MyGroupViewHolder holder, int groupPosition, int viewType) {
        // set text
        holder.mTextView.setText("Group " + groupPosition);
        // mark as clickable
        holder.itemView.setClickable(true);

        // set background resource (target view ID: container)
        final int expandState = holder.getExpandStateFlags();

        if ((expandState & Expandable.STATE_FLAG_IS_UPDATED) != 0) {
            int bgResId;
            boolean isExpanded;
            boolean animateIndicator = ((expandState & Expandable.STATE_FLAG_HAS_EXPANDED_STATE_CHANGED) != 0);

            isExpanded = (expandState & Expandable.STATE_FLAG_IS_EXPANDED) != 0;

//            holder.mContainer.setBackgroundResource(bgResId);
            holder.mIndicator.setExpandedState(isExpanded, animateIndicator);
        } else {
            Log.d("TAG", "teste");
        }
    }

    @Override
    public void onBindChildViewHolder(MyChildViewHolder holder, int groupPosition, int childPosition, int viewType) {

        // set text
        holder.mTextView.setText("Child " + childPosition);

        // set background resource (target view ID: container)
//        int bgResId;
//        bgResId = R.drawable.bg_item_normal_state;
//        holder.mContainer.setBackgroundResource(bgResId);
    }

    @Override
    public boolean onCheckCanExpandOrCollapseGroup(MyGroupViewHolder holder, int groupPosition, int x, int y, boolean expand) {
        // check is enabled
        return holder.itemView.isEnabled() && holder.itemView.isClickable();

    }

    // NOTE: Make accessible with short name
    private interface Expandable extends ExpandableItemConstants {
    }

    public static abstract class MyBaseViewHolder extends AbstractExpandableItemViewHolder {
        public FrameLayout mContainer;
        public TextView mTextView;

        public MyBaseViewHolder(View v) {
            super(v);
            mContainer = (FrameLayout) v.findViewById(R.id.container);
            mTextView = (TextView) v.findViewById(android.R.id.text1);
        }
    }

    public static class MyGroupViewHolder extends MyBaseViewHolder {
        public ExpandableItemIndicator mIndicator;

        public MyGroupViewHolder(View v) {
            super(v);
            mIndicator = (ExpandableItemIndicator) v.findViewById(R.id.indicator);
        }
    }

    public static class MyChildViewHolder extends MyBaseViewHolder {
        public MyChildViewHolder(View v) {
            super(v);
        }
    }

    // NOTE: This method is called from Fragment
//    public void addGroupAndChildItemsBottom(int groupCount, int childCount) {
//        int groupPosition = mProvider.getGroupCount();
//
//        for (int i = 0; i < groupCount; i++) {
//            // add group
//            mProvider.addGroupItem(groupPosition + i);
//            // add children
//            for (int j = 0; j < childCount; j++) {
//                mProvider.addChildItem(groupPosition + i, j);
//            }
//        }
//
//        mExpandableItemManager.notifyGroupItemRangeInserted(groupPosition, groupCount, true);
//    }

    // NOTE: This method is called from Fragment
//    public void removeGroupItemsBottom(int count) {
//        int groupCount = mProvider.getGroupCount();
//
//        count = Math.min(count, groupCount);
//
//        int groupPosition = groupCount - count;
//
//        for (int i = 0; i < count; i++) {
//            mProvider.removeGroupItem(groupPosition);
//        }
//
//        mExpandableItemManager.notifyGroupItemRangeRemoved(groupPosition, count);
//    }

    // NOTE: This method is called from Fragment
//    public void clearGroupItems() {
//        int groupCount = mProvider.getGroupCount();
//
//        mProvider.clear();
//
//        mExpandableItemManager.notifyGroupItemRangeRemoved(0, groupCount);
//    }
}

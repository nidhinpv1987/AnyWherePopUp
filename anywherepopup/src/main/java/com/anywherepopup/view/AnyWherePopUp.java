package com.anywherepopup.view;


import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class AnyWherePopUp {

	int xPos, yPos;
	// act;
	private WindowManager mWindowManager;
	private PopupWindow changeStatusPopUp;

	private LayoutInflater layoutInflater;
	Context con;
	/**
	 * Shows the  inflated view that is in the id at best possible available location in
	 * the view
	 *
	 * @param con
	 *            The Context of the corresponding Activity
	 */
	public AnyWherePopUp(Context con) {
		super();
		this.con = con;
		changeStatusPopUp = new PopupWindow(con);
		mWindowManager = (WindowManager) con
				.getSystemService(Context.WINDOW_SERVICE);

		layoutInflater = (LayoutInflater) con
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	/**
	 * Shows the  inflated view that is in the id at best possible available location in
	 * the view
	 *
	 * @param view
	 *            The view of the layout where the popup should be shown
	 * @param id
	 *            The id of the view to be shown at the selected position
	 */

	@SuppressWarnings("deprecation")
	public void show(View view, int id) {
		View mRootView = layoutInflater.inflate(id, null);
		mRootView.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		int rootHeight = mRootView.getMeasuredHeight();
		int rootWidth = 0;
		if (rootWidth == 0) {
			rootWidth = mRootView.getMeasuredWidth();
		}
		int[] location = new int[2];
		int screenWidth = mWindowManager.getDefaultDisplay().getWidth();
		int screenHeight = mWindowManager.getDefaultDisplay().getHeight();

		view.getLocationOnScreen(location);

		Rect anchorRect = new Rect(location[0], location[1], location[0]
				+ view.getWidth(), location[1] + view.getHeight());
		// automatically get X coord of popup (top left)
		if ((anchorRect.left + rootWidth) > screenWidth) {
			xPos = anchorRect.left - (rootWidth - view.getWidth());
			xPos = (xPos < 0) ? 0 : xPos;

		} else {
			if (view.getWidth() > rootWidth) {
				xPos = anchorRect.centerX() - (rootWidth / 2);
			} else {
				xPos = anchorRect.left;
			}

		}

		int dyTop = anchorRect.top;
		int dyBottom = screenHeight - anchorRect.bottom;

		boolean onTop = (dyTop > dyBottom) ? true : false;

		if (onTop) {
			if (rootHeight > dyTop) {
				yPos = 15;

			} else {
				yPos = anchorRect.top - rootHeight;
			}
		} else {
			yPos = anchorRect.bottom;

		}

		DisplayMetrics displaymetrics = new DisplayMetrics();
		mWindowManager.getDefaultDisplay().getMetrics(displaymetrics);
		changeStatusPopUp.setFocusable(true);
		changeStatusPopUp.setContentView(mRootView);
		changeStatusPopUp.setBackgroundDrawable(new BitmapDrawable());
		changeStatusPopUp.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
		changeStatusPopUp.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
		changeStatusPopUp.showAtLocation(mRootView, Gravity.NO_GRAVITY, xPos,
				yPos);

	}

	/**
	 * Dismisses the  inflated view.
	 */
	public void dismiss() {
		changeStatusPopUp.dismiss();
	}

	/**
	 * To set animation for the popup entry and exit
	 * @param animStyle
	 *            Refer the style you need to show in the animation.
	 *            Use Styles AnimationStyle.FADE
	 *                       AnimationStyle.SLIDE_TOP
	 *
	 * @see     com.anywherepopup.view.AnimationStyle
	 */
	public void setAnimation(int animStyle) {
		// TODO Auto-generated method stub
		changeStatusPopUp.setAnimationStyle(animStyle);
	}

	/**
	 * To get the view of the poup
	 * @return Return the view of the popup
	 * @see View
	 */
	public View getView() {
		return changeStatusPopUp.getContentView();

	}

}

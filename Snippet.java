//android.support.v7.widget
//API 20 UI com.example.android.actionbarcompat.listpopupmenu
 PopupMenu popup = new PopupMenu(getActivity(), view);

        // Inflate our menu resource into the PopupMenu's Menu
        popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());

        // Set a listener so we are notified if a menu item is clicked
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_remove:
                        // Remove the item from the adapter
                        adapter.remove(item);
                        return true;
                }
                return false;
            }
        });

//API 20 UI com.example.android.textswitcher
// TextSwitcher Sample 
//demoImage: TextSwitcher.png
   // 1. find id and Set the factory used to create TextViews to switch between.
        mSwitcher.setFactory(mFactory);

        /*
         * Set the in and out animations. Using the fade_in/out animations
         * provided by the framework.
         */
        Animation in = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right);
        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);


        /*
         * Setup the 'next' button. The counter is incremented when clicked and
         * the new value is displayed in the TextSwitcher. The change of text is
         * automatically animated using the in/out animations set above.
         */
        Button nextButton = (Button) findViewById(R.id.button);
        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mCounter++;

                mSwitcher.setText(String.valueOf(mCounter));

            }
        });

        // Set the initial text without an animation
        mSwitcher.setCurrentText(String.valueOf(mCounter));
        
            /**
     * The {@link android.widget.ViewSwitcher.ViewFactory} used to create {@link android.widget.TextView}s that the
     * {@link android.widget.TextSwitcher} will switch between.
     */
    private ViewFactory mFactory = new ViewFactory() {

        @Override
        public View makeView() {

            // Create a new TextView
            TextView t = new TextView(MainActivity.this);
            t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
            t.setTextAppearance(MainActivity.this, android.R.style.TextAppearance_Large);
            return t;
        }
    };

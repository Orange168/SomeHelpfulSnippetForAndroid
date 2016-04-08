 class MyThread extends Thread {

        List<EquipmentInfo> mList;

        public MyThread(List<EquipmentInfo> mList) {
            this.mList = mList;
        }

        public void run() {
            Cursor c = null;
            if (mList.size() > 0) {
                try {
                    for (int i = 0; i < mList.size(); i++) {
                        c = getContentResolver().query(Uri.withAppendedPath(
                                ContactsContract.PhoneLookup.CONTENT_FILTER_URI, mList.get(i).getNumber()), new String[]{
                                ContactsContract.PhoneLookup._ID,
                                ContactsContract.PhoneLookup.NUMBER,
                                ContactsContract.PhoneLookup.DISPLAY_NAME,
                                ContactsContract.PhoneLookup.TYPE, ContactsContract.PhoneLookup.LABEL}, null, null, null);
                        if (c != null && c.getCount() == 0) {
                            Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
                            ContentResolver resolver = getContentResolver();
                            ContentValues values = new ContentValues();
                            long id = ContentUris.parseId(resolver.insert(uri, values));

                            if (mList.get(i).getName().equals("null")) {

                                //添加联系人姓名
                                uri = Uri.parse("content://com.android.contacts/data");
                                values.put("raw_contact_id", id);
                                values.put("data2", mList.get(i).getNumber() + "的易孝宝");
                                values.put("mimetype", "vnd.android.cursor.item/name");
                                resolver.insert(uri, values);

                                //添加联系人电话
                                values.clear(); // 清空上次的数据
                                values.put("raw_contact_id", id);
                                values.put("data1", mList.get(i).getNumber());
                                values.put("data2", "2");
                                values.put("mimetype", "vnd.android.cursor.item/phone_v2");
                                resolver.insert(uri, values);

                            } else {

                                if (mList.get(i).getName() != null && mList.get(i).getName().length() > 0) {
                                    uri = Uri.parse("content://com.android.contacts/data");
                                    values.put("raw_contact_id", id);
                                    values.put("mimetype", "vnd.android.cursor.item/name");
                                    values.put("data2", mList.get(i).getName() + "的易孝宝");
                                    resolver.insert(uri, values);
                                }

                                if (mList.get(i).getNumber() != null && mList.get(i).getNumber().length() > 0) {
                                    // 添加电话
                                    values.clear();
                                    values.put("raw_contact_id", id);
                                    values.put("mimetype", "vnd.android.cursor.item/phone_v2");
                                    values.put("data2", "2");
                                    values.put("data1", mList.get(i).getNumber());
                                    resolver.insert(uri, values);

                                }

                            }
                            c.close();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

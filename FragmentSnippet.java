transaction.add(R.id.content, IndexFragment); 
transaction.add(R.id.content, IndexFragment,”Tab1″);
IndexFragment＝FragmentManager.findFragmentByTag(“Tab1″);
transaction.hide(otherfragment);
transaction.show(thisfragment);

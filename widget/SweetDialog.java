//compile 'cn.pedant.sweetalert:library:1.3'
//https://github.com/pedant/sweet-alert-dialog
public class SweetDialog extends SweetAlertDialog {

        private CountDownTimer timer ;
        int tick = 0 ;
        private int intervalTime = 800;
        private int duration = 800 * 30;
        public SweetDialog(Context context, int alertType) {
            super(context, alertType);
            timer = new CountDownTimer(duration, intervalTime) {
                @Override
                public void onTick(long millisUntilFinished) {
                    getProgressHelper().setBarColor(context.getResources().getColor(
                            tick == 0 ? R.color.blue_btn_bg_color :
                                    tick == 1 ? R.color.material_deep_teal_50 :
                                            R.color.success_stroke_color
                    ));
                    tick++;
                }

                @Override
                public void onFinish() {
                    tick = 0;
                    if (isShowing()){
                        dismiss();
                        Toast.makeText(context,"链接超时，请检测网络或稍后在试",Toast.LENGTH_LONG).show();
                    }
                }
            };
        }

        @Override
        public void show() {
            super.show();
            timer.start();
        }


        public void setDuration(int duration) {
            this.duration = duration;
        }

        public void setIntervalTime(int intervalTime) {
            this.intervalTime = intervalTime;
        }
}

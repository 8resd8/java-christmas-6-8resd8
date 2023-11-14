package christmas;

import christmas.io.InputView;
import christmas.io.OutputView;
import christmas.order.EventDiscount;
import christmas.order.OrderData;
import christmas.order.TotalOrderAmount;
import christmas.util.ChangeDay;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        ChangeDay changedDay = new ChangeDay();
        OutputView outputView = new OutputView();
        OrderData orderData = new OrderData();
        EventDiscount eventDiscount = new EventDiscount();
        TotalOrderAmount totalOrderAmount = new TotalOrderAmount();

        ChristmasPromotion christmasPromotion = new ChristmasPromotion(
                inputView, changedDay, outputView, orderData, eventDiscount, totalOrderAmount
        );
        christmasPromotion.eventStart();
    }
}

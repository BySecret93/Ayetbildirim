# Ayet Bildirimi

Android 8+ için hazırlanmış basit bir uygulamadır.

Telefon kilidinden çıkarıldığında `USER_PRESENT` olayı yakalanır ve sıradaki kısa ayet bildirim olarak gösterilir.

## Kurulum
1. Android Studio'da projeyi açın.
2. Gradle senkronizasyonunu tamamlayın.
3. Telefonu USB hata ayıklama ile bağlayıp Run'a basın.
4. Android 13+ cihazlarda bildirim iznini verin.
5. Kilit ekranından çıkıp tekrar deneyin.

Not: Bazı Samsung/Android sürümlerinde pil optimizasyonu bildirimlerin arka planda çalışmasını etkileyebilir. Böyle bir durumda uygulama için pil kullanımını "Sınırsız" yapmak gerekebilir.

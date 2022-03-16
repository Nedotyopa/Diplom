import { StatisticPageModule } from './statistic-page.module';

describe('StatisticPageModule', () => {
  let statisticPageModule: StatisticPageModule;

  beforeEach(() => {
    statisticPageModule = new StatisticPageModule();
  });

  it('should create an instance', () => {
    expect(statisticPageModule).toBeTruthy();
  });
});

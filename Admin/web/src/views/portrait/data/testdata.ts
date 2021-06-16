interface GroupItem {
    title: string;
    icon: string;
    color: string;
    desc: string;
    date: string;
    group: string;
}

export const groupItems: GroupItem[] = [
    {
        title: 'Github',
        icon: 'carbon:logo-github',
        color: '',
        desc: '不要等待机会，而要创造机会。',
        group: '开源组',
        date: '2021-04-01',
    },
    {
        title: 'Vue',
        icon: 'ion:logo-vue',
        color: '#3fb27f',
        desc: '现在的你决定将来的你。',
        group: '算法组',
        date: '2021-04-01',
    },
    {
        title: 'Html5',
        icon: 'ion:logo-html5',
        color: '#e18525',
        desc: '没有什么才能比努力更重要。',
        group: '上班摸鱼',
        date: '2021-04-01',
    },
    {
        title: 'Angular',
        icon: 'ion:logo-angular',
        color: '#bf0c2c',
        desc: '热情和欲望可以突破一切难关。',
        group: 'UI',
        date: '2021-04-01',
    },
    {
        title: 'React',
        icon: 'bx:bxl-react',
        color: '#00d8ff',
        desc: '健康的身体是实目标的基石。',
        group: '技术牛',
        date: '2021-04-01',
    },
    {
        title: 'Js',
        icon: 'ion:logo-javascript',
        color: '#4daf1bc9',
        desc: '路是走出来的，而不是空想出来的。',
        group: '架构组',
        date: '2021-04-01',
    },
];
